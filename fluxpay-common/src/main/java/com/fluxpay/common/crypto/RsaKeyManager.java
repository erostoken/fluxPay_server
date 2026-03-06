package com.fluxpay.common.crypto;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * RSA 密钥管理器
 *
 * <p>启动时按以下优先级加载密钥：
 * <ol>
 *   <li>读取配置 {@code fluxpay.crypto.public-key} / {@code fluxpay.crypto.private-key}（Base64）</li>
 *   <li>配置为空时自动生成密钥对（每次重启密钥变化，适合开发环境）</li>
 * </ol>
 *
 * <p><b>生产环境建议</b>：通过环境变量或配置中心注入固定密钥，保证多实例部署时公钥一致。
 */
@Slf4j
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(CryptoProperties.class)
public class RsaKeyManager {

    private final CryptoProperties cryptoProperties;

    @Getter
    private PublicKey  publicKey;
    @Getter
    private PrivateKey privateKey;
    /** Base64 编码的公钥，前端通过接口获取 */
    @Getter
    private String publicKeyBase64;

    @PostConstruct
    public void init() {
        if (!cryptoProperties.isEnabled()) {
            log.info("[Crypto] 加解密已禁用，跳过 RSA 密钥初始化");
            return;
        }
        if (StringUtils.hasText(cryptoProperties.getPublicKey())
                && StringUtils.hasText(cryptoProperties.getPrivateKey())) {
            // 加载配置中的固定密钥
            this.publicKey  = RsaUtils.loadPublicKey(cryptoProperties.getPublicKey());
            this.privateKey = RsaUtils.loadPrivateKey(cryptoProperties.getPrivateKey());
            this.publicKeyBase64 = cryptoProperties.getPublicKey();
            log.info("[Crypto] RSA 密钥已从配置加载，keySize={}", cryptoProperties.getRsaKeySize());
        } else {
            // 自动生成临时密钥对
            KeyPair keyPair = RsaUtils.generateKeyPair(cryptoProperties.getRsaKeySize());
            this.publicKey  = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
            this.publicKeyBase64 = RsaUtils.toBase64(publicKey);
            log.info("[Crypto] RSA 密钥对已自动生成，keySize={}（开发模式，重启密钥会变化）",
                    cryptoProperties.getRsaKeySize());
        }
    }
}
