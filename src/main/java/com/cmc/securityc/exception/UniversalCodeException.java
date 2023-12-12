package com.cmc.securityc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class UniversalCodeException extends RuntimeException{
    @Getter
    private Integer code;
    public UniversalCodeException(Integer httpStatus, String msg) {
        super(msg,null,false,false);
        this.code = httpStatus;
    }

    public UniversalCodeException(HttpStatus httpStatus, String msg) {
        super(msg,null,false,false);
        this.code = httpStatus.value();
    }
}
