package com.fluxpay.admin.controller.crypto;

import com.fluxpay.common.crypto.RsaKeyManager;
import com.fluxpay.common.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 加解密公钥接口
 *
 * <p>前端在登录或提交敏感信息前，先请求此接口获取 RSA 公钥，
 * 再用公钥加密密码、手机号等字段后提交。
 */
@RestController
@RequestMapping("/crypto")
@RequiredArgsConstructor
public class CryptoController {

    private final RsaKeyManager rsaKeyManager;

    /**
     * 获取 RSA 公钥
     * GET /crypto/public-key
     *
     * @return {@code { "publicKey": "Base64EncodedPublicKey" }}
     */
    @GetMapping("/public-key")
    public Result<Map<String, String>> publicKey() {
        return Result.ok(Map.of("publicKey", rsaKeyManager.getPublicKeyBase64()));
    }
}
