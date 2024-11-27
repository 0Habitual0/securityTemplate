package com.habitual.demo.common.exception;

import lombok.Getter;

/**
 * 自定义异常 Token验证异常
 */
@Getter
public class TokenValidationException extends RuntimeException {

    private final Integer errorCode;

    public TokenValidationException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
