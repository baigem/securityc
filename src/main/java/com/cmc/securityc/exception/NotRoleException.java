package com.cmc.securityc.exception;

public class NotRoleException extends RuntimeException{
    public NotRoleException(String role) {
        super("Not role"+role,null,false,false);
    }

    public NotRoleException(String[] roles) {
        super("Not role"+String.join(",",roles),null,false,false);
    }
}
