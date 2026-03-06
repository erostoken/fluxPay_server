package com.fluxpay.admin.dto.auth;

import lombok.Builder;
import lombok.Data;

/**
 * 当前登录用户信息
 */
@Data
@Builder
public class UserInfoVO {

    private Long   id;
    private String username;
    private String phone;
    /** 状态：0-禁用 1-启用 */
    private Integer status;
}
