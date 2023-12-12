package com.cmc.securityc.exception;

public class NotRoleException extends RuntimeException{
    public NotRoleException(String role) {
        super("need role "+role,null,false,false);
    }

    public NotRoleException(String[] roles) {
        super("need role "+String.join(",",roles),null,false,false);
    }
}
