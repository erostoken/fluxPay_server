package com.fluxpay.common.crypto;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

/**
 * RSA 工具类
 *
 * <p>使用 RSA/ECB/OAEPWithSHA-256AndMGF1Padding 填充方案，安全性优于 PKCS1。
 * RSA-2048 最大可加密约 190 字节明文，足以覆盖密码、手机号等短字段。
 *
 * <p>前端对应实现（浏览器端）：
 * <pre>
 *   const encrypted = await window.crypto.subtle.encrypt(
 *     { name: "RSA-OAEP" },
 *     publicKey,          // CryptoKey，由 Base64 导入
 *     new TextEncoder().encode(plaintext)
 *   );
 *   // 结果转 Base64 后放入请求字段
 * </pre>
 */
public class RsaUtils {

    private static final String ALGORITHM = "RSA";
    private static final String TRANSFORMATION = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    private RsaUtils() {
    }

    // ── 密钥生成 ──────────────────────────────────────────────────────────

    /**
     * 生成 RSA 密钥对
     */
    public static KeyPair generateKeyPair(int keySize) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
            generator.initialize(keySize, new SecureRandom());
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("RSA 密钥生成失败", e);
        }
    }

    // ── 密钥序列化 ────────────────────────────────────────────────────────

    /**
     * PublicKey → Base64 字符串
     */
    public static String toBase64(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    /**
     * PrivateKey → Base64 字符串
     */
    public static String toBase64(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    // ── 密钥加载 ──────────────────────────────────────────────────────────

    /**
     * Base64 字符串 → PublicKey
     */
    public static PublicKey loadPublicKey(String base64) {
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            return KeyFactory.getInstance(ALGORITHM)
                    .generatePublic(new X509EncodedKeySpec(bytes));
        } catch (Exception e) {
            throw new IllegalArgumentException("RSA 公钥加载失败", e);
        }
    }

    /**
     * Base64 字符串 → PrivateKey
     */
    public static PrivateKey loadPrivateKey(String base64) {
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            return KeyFactory.getInstance(ALGORITHM)
                    .generatePrivate(new PKCS8EncodedKeySpec(bytes));
        } catch (Exception e) {
            throw new IllegalArgumentException("RSA 私钥加载失败", e);
        }
    }

    // ── 加解密 ────────────────────────────────────────────────────────────

    /**
     * 公钥加密（前端加密 / 服务端测试用）
     *
     * @param plaintext 明文字符串（UTF-8）
     * @param publicKey RSA 公钥
     * @return Base64 编码的密文
     */
    public static String encrypt(String plaintext, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            OAEPParameterSpec spec = new OAEPParameterSpec(
                    "SHA-256",                // Hash 算法
                    "MGF1",                   // Mask Generation Function
                    MGF1ParameterSpec.SHA256, // MGF1 使用的 Hash
                    PSource.PSpecified.DEFAULT // Label (默认为空)
            );
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, spec);
            byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new IllegalArgumentException("RSA 加密失败", e);
        }
    }

    /**
     * 私钥解密（服务端解密前端提交的密文）
     *
     * @param base64Ciphertext Base64 编码的密文
     * @param privateKey       RSA 私钥
     * @return 明文字符串（UTF-8）
     */
    public static String decrypt(String base64Ciphertext, PrivateKey privateKey) {
        try {
            byte[] encrypted = Base64.getDecoder().decode(base64Ciphertext);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            OAEPParameterSpec spec = new OAEPParameterSpec(
                    "SHA-256",
                    "MGF1",
                    MGF1ParameterSpec.SHA256,
                    PSource.PSpecified.DEFAULT
            );
            cipher.init(Cipher.DECRYPT_MODE, privateKey, spec);
            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted, java.nio.charset.StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalArgumentException("RSA 解密失败", e);
        }
    }
}
