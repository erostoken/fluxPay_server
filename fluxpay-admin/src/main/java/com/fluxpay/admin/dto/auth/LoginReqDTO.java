package com.fluxpay.admin.dto.auth;

import com.fluxpay.common.crypto.annotation.EncryptedField;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登录请求
 *
 * <p>{@code password} 字段需前端使用 RSA 公钥加密后传入（Base64 编码）。
 */
@Data
public class LoginReqDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    /** 前端用 RSA 公钥加密，后端 AOP 自动解密 */
    @EncryptedField
    @NotBlank(message = "密码不能为空")
    private String password;
}
