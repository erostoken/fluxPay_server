/**
 * 异常定义与全局异常处理。
 *
 * <p>{@code BusinessException} 为业务异常基类，携带 {@code ResultCode} 错误码；
 * {@code GlobalExceptionHandler} 通过 {@code @RestControllerAdvice} 统一捕获并转换为标准响应。
 */
package com.fluxpay.common.exception;
