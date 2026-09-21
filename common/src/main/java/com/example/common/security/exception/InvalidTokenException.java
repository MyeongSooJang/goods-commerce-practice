package com.example.common.security.exception;

import com.example.common.exception.BusinessException;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException() {
        super("Invalid token.", 401);
    }
}
