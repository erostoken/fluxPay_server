package com.fluxpay.common.crypto.aspect;

import com.fluxpay.common.crypto.CryptoProperties;
import com.fluxpay.common.crypto.RsaKeyManager;
import com.fluxpay.common.crypto.RsaUtils;
import com.fluxpay.common.crypto.annotation.EncryptedField;
import com.fluxpay.common.exception.BusinessException;
import com.fluxpay.common.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字段解密切面
 *
 * <p>拦截所有 {@code @RestController} 方法，对入参对象中标注了
 * {@link EncryptedField} 的字符串字段执行 RSA 解密。
 *
 * <p>解密流程：
 * <pre>
 *   1. 前端用 RSA 公钥加密敏感字段（密码、手机号等），明文 → Base64 密文
 *   2. 发送请求，加密字段以 Base64 字符串传入
 *   3. 本切面在方法执行前解密，业务层直接使用明文
 * </pre>
 *
 * <p>若 {@code fluxpay.crypto.enabled=false} 或字段值为空，则跳过解密。
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class FieldDecryptAspect {

    private final RsaKeyManager    rsaKeyManager;
    private final CryptoProperties cryptoProperties;

    /** 切点：所有 @RestController 标注类的公共方法 */
    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void decryptRequestFields(JoinPoint joinPoint) {
        if (!cryptoProperties.isEnabled()) {
            return;
        }
        for (Object arg : joinPoint.getArgs()) {
            if (arg == null) continue;
            // 跳过基本类型、包装类、String 及常见框架对象
            if (isSkippable(arg)) continue;
            decryptObject(arg);
        }
    }

    // ── 私有方法 ──────────────────────────────────────────────────────────

    private void decryptObject(Object obj) {
        for (Field field : getAllFields(obj.getClass())) {
            if (!field.isAnnotationPresent(EncryptedField.class)) continue;
            if (field.getType() != String.class) continue;

            field.setAccessible(true);
            try {
                String ciphertext = (String) field.get(obj);
                if (!StringUtils.hasText(ciphertext)) continue;

                String plaintext = RsaUtils.decrypt(ciphertext, rsaKeyManager.getPrivateKey());
                field.set(obj, plaintext);
                log.debug("[Crypto] 字段解密成功: {}.{}", obj.getClass().getSimpleName(), field.getName());
            } catch (IllegalArgumentException e) {
                log.warn("[Crypto] 字段解密失败: {}.{}", obj.getClass().getSimpleName(), field.getName());
                throw new BusinessException(ResultCode.BAD_REQUEST, "参数解密失败，请刷新公钥后重试");
            } catch (IllegalAccessException e) {
                log.error("[Crypto] 字段访问异常: {}", field.getName(), e);
            }
        }
    }

    /** 递归获取类及其父类所有字段 */
    private List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        while (clazz != null && clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    /** 跳过不需要处理的参数类型 */
    private boolean isSkippable(Object obj) {
        Class<?> clazz = obj.getClass();
        return clazz.isPrimitive()
                || clazz.isEnum()
                || clazz.isArray()
                || obj instanceof String
                || obj instanceof Number
                || obj instanceof Boolean
                || obj instanceof java.util.Collection
                || obj instanceof java.util.Map
                || clazz.getName().startsWith("org.springframework")
                || clazz.getName().startsWith("jakarta.servlet");
    }
}
