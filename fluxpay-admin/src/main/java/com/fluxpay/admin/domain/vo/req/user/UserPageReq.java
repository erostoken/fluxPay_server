package com.fluxpay.admin.domain.vo.req.user;

import com.fluxpay.common.dto.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户分页查询请求
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageReq extends PageDTO {

    /** 用户名（模糊匹配） */
    private String username;

    /** 手机号（模糊匹配） */
    private String phone;

    /** 状态：0-禁用 1-启用 */
    private Integer status;
}
