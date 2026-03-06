package com.fluxpay.admin.dto.user;

import com.fluxpay.common.crypto.annotation.EncryptedField;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 修改管理员用户请求（密码、状态可单独更新）
 */
@Data
public class UserUpdateReqDTO {

    /** 手机号（前端 RSA 加密后传入） */
    @EncryptedField
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    /** 新密码（前端 RSA 加密，为空时不更新密码） */
    @EncryptedField
    @Size(min = 6, max = 64, message = "密码长度 6~64")
    private String password;

    /** 状态：0-禁用 1-启用 */
    private Integer status;
}
