package com.cmc.securityc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 角色认证：必须具有指定角色标识才能进入该方法
 *
 * @author 程梦城
 * @version 1.0.0
 * &#064;date  2023/12/12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RequiresRoles {
    /**
     * 需要校验的角色标识
     */
    String[] value() default {};

    /**
     * 验证逻辑：AND | OR，默认AND
     */
    Logical logical() default Logical.AND;
}
