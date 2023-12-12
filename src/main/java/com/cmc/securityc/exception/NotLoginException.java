package com.cmc.securityc.exception;

public class NotLoginException extends RuntimeException{
    public NotLoginException(String msg) {
        super(msg,null,false,false);
    }
}
