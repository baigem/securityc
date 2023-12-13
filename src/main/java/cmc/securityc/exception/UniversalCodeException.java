package cmc.securityc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UniversalCodeException extends RuntimeException{
    private final Integer code;
    public UniversalCodeException(Integer httpStatus, String msg) {
        super(msg,null,false,false);
        this.code = httpStatus;
    }

    public UniversalCodeException(HttpStatus httpStatus, String msg) {
        super(msg,null,false,false);
        this.code = httpStatus.value();
    }
}
