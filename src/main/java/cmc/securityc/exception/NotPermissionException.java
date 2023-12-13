package cmc.securityc.exception;

public class NotPermissionException extends RuntimeException{
    public NotPermissionException(String msg) {
        super(msg,null,false,false);
    }

    public NotPermissionException(String[] permissions) {
        super("NotPermissionException"+String.join(",",permissions),null,false,false);
    }
}
