package com.cmc.securityc.exception;

/**
 * 内部auth异常
 *
 * @author 程梦城
 * @version 1.0.0
 * &#064;date  2023/12/12
 */
public class InnerAuthException extends RuntimeException{
    public InnerAuthException(String msg) {
        super(msg,null, false,false);
    }
}
