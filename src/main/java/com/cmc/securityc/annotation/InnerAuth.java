package com.cmc.securityc.annotation;

import java.lang.annotation.*;

/**
 * 内部认证注解
 *
 * @author 程梦城
 * @version 1.0.0
 * &#064;date  2023/12/12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InnerAuth {
    /**
     * 是否校验用户信息
     */
    boolean isUser() default false;
}