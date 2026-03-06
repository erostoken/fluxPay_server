package com.fluxpay.common.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * 通用分页请求 DTO
 */
@Data
public class PageDTO {

    @Min(value = 1, message = "页码最小为1")
    private int page = 1;

    @Min(value = 1, message = "每页条数最小为1")
    @Max(value = 100, message = "每页条数最大为100")
    private int size = 20;

    /** 计算 MyBatis-Plus 的 offset */
    public long offset() {
        return (long) (page - 1) * size;
    }
}
