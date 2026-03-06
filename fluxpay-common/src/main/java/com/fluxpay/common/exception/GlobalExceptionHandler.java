package com.fluxpay.common.exception;

import com.fluxpay.common.result.Result;
import com.fluxpay.common.result.ResultCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 * <p>统一捕获以下异常并转换为 {@link Result} 标准响应：
 * <ul>
 *   <li>{@link BusinessException} — 业务异常</li>
 *   <li>{@link MethodArgumentNotValidException} — @Valid 参数校验失败</li>
 *   <li>{@link ConstraintViolationException} — @Validated 路径/查询参数校验失败</li>
 *   <li>{@link HttpRequestMethodNotSupportedException} — 请求方法不支持</li>
 *   <li>{@link Exception} — 未知异常兜底</li>
 * </ul>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> handleBusinessException(BusinessException ex) {
        log.warn("BusinessException: code={}, message={}", ex.getCode(), ex.getMessage());
        return Result.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * @Valid 注解触发的参数校验失败（RequestBody）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> handleValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        log.warn("MethodArgumentNotValidException: {}", message);
        return Result.fail(ResultCode.BAD_REQUEST, message);
    }

    /**
     * @Validated 注解触发的参数校验失败（路径参数 / 查询参数）
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> handleConstraintViolationException(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + " " + v.getMessage())
                .collect(Collectors.joining("; "));
        log.warn("ConstraintViolationException: {}", message);
        return Result.fail(ResultCode.BAD_REQUEST, message);
    }

    /**
     * 缺少必填请求参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> handleMissingParam(MissingServletRequestParameterException ex) {
        String message = "缺少必填参数: " + ex.getParameterName();
        log.warn("MissingServletRequestParameterException: {}", message);
        return Result.fail(ResultCode.BAD_REQUEST, message);
    }

    /**
     * 参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "参数类型错误: " + ex.getName();
        log.warn("MethodArgumentTypeMismatchException: {}", message);
        return Result.fail(ResultCode.BAD_REQUEST, message);
    }

    /**
     * 请求方法不支持（405）
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        log.warn("HttpRequestMethodNotSupportedException: {}", ex.getMessage());
        return Result.fail(ResultCode.METHOD_NOT_ALLOWED);
    }

    /**
     * 未知异常兜底（500）
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> handleException(Exception ex) {
        log.error("Unexpected exception", ex);
        return Result.fail(ResultCode.INTERNAL_SERVER_ERROR);
    }
}
