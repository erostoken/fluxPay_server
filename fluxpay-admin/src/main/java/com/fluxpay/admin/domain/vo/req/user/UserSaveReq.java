package com.fluxpay.admin.domain.vo.req.user;

import com.fluxpay.common.crypto.annotation.EncryptedField;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 新建管理员用户请求
 */
@Data
public class UserSaveReq {

    /** 登录用户名 */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 32, message = "用户名长度 3~32")
    private String username;

    /** 初始密码（前端 RSA 加密后传入，后端解密后 BCrypt 存储） */
    @EncryptedField
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 64, message = "密码长度 6~64")
    private String password;

    /** 手机号（前端 RSA 加密后传入） */
    @EncryptedField
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    /** 状态：0-禁用 1-启用，默认启用 */
    private Integer status = 1;
}
