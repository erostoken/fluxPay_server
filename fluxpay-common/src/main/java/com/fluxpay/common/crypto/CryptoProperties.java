package com.fluxpay.common.crypto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 加解密配置项，绑定 {@code fluxpay.crypto.*}
 */
@Data
@ConfigurationProperties(prefix = "fluxpay.crypto")
public class CryptoProperties {

    /** 是否启用前后端加解密，默认开启 */
    private boolean enabled = true;

    /** RSA 密钥长度，默认 2048 */
    private int rsaKeySize = 2048;

    /**
     * 固定 RSA 公钥（Base64），为空则服务启动时自动生成。
     * 生产环境建议通过环境变量或配置中心注入以保证密钥一致性。
     */
    private String publicKey;

    /**
     * 固定 RSA 私钥（Base64），与 publicKey 配对使用。
     */
    private String privateKey;
}
