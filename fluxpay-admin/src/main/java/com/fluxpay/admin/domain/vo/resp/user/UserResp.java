package com.fluxpay.admin.domain.vo.resp.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户信息响应
 */
@Data
public class UserResp {

    private Long id;
    private String username;
    private String phone;
    /** 状态：0-禁用 1-启用 */
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date lastLoginTime;
    private String lastLoginIp;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createdTime;

    /** 拥有的角色列表（ID） */
    private List<Long> roleIds;
}
