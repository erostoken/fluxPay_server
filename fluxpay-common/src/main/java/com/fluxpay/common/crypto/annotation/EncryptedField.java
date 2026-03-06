package com.fluxpay.common.crypto.annotation;

import java.lang.annotation.*;

/**
 * 标记需要前端加密传输的字符串字段。
 *
 * <p>前端使用服务端 RSA 公钥对该字段进行加密后，以 Base64 字符串传入；
 * 服务端 {@link com.fluxpay.common.crypto.aspect.FieldDecryptAspect} 在进入
 * Controller 方法前自动解密，业务层直接使用明文。
 *
 * <p>用法示例：
 * <pre>
 *   public class LoginReqDTO {
 *       private String username;
 *
 *       &#64;EncryptedField
 *       private String password;
 *   }
 * </pre>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncryptedField {
}
