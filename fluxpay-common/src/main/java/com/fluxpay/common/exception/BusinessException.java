package com.fluxpay.common.exception;

import com.fluxpay.common.result.ResultCode;
import lombok.Getter;

/**
 * 业务异常基类
 *
 * <p>所有业务逻辑抛出的受控异常均继承此类，
 * 由 {@link GlobalExceptionHandler} 统一捕获并转换为标准响应。
 */
@Getter
public class BusinessException extends RuntimeException {

    private final int code;
    private final String message;

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BusinessException(ResultCode resultCode, String message) {
        super(message);
        this.code = resultCode.getCode();
        this.message = message;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    // ── 静态工厂方法（链式调用更简洁） ──────────────────────────

    public static BusinessException of(ResultCode resultCode) {
        return new BusinessException(resultCode);
    }

    public static BusinessException of(ResultCode resultCode, String message) {
        return new BusinessException(resultCode, message);
    }
}
