package com.fluxpay.common.result;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一响应体
 *
 * @param <T> 数据类型
 */
@Data
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 业务状态码 */
    private int code;

    /** 提示信息 */
    private String message;

    /** 响应数据 */
    private T data;

    private Result() {
    }

    // ── 成功 ──────────────────────────────────────────────────

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.code = ResultCode.SUCCESS.getCode();
        result.message = ResultCode.SUCCESS.getMessage();
        result.data = data;
        return result;
    }

    public static <T> Result<T> ok(T data, String message) {
        Result<T> result = new Result<>();
        result.code = ResultCode.SUCCESS.getCode();
        result.message = message;
        result.data = data;
        return result;
    }

    // ── 失败 ──────────────────────────────────────────────────

    public static <T> Result<T> fail(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.code = resultCode.getCode();
        result.message = resultCode.getMessage();
        return result;
    }

    public static <T> Result<T> fail(ResultCode resultCode, String message) {
        Result<T> result = new Result<>();
        result.code = resultCode.getCode();
        result.message = message;
        return result;
    }

    public static <T> Result<T> fail(int code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        return result;
    }

    // ── 工具方法 ──────────────────────────────────────────────

    public boolean isSuccess() {
        return this.code == ResultCode.SUCCESS.getCode();
    }
}
