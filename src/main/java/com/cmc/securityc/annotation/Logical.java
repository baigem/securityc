package com.cmc.securityc.annotation;

/**
 * 权限注解的验证模式
 *
 * @author 程梦城
 * @version 1.0.0
 * &#064;date  2023/12/12
 */
public enum Logical {
    /**
     * 必须具有所有的元素
     */
    AND,

    /**
     * 只需具有其中一个元素
     */
    OR
}
