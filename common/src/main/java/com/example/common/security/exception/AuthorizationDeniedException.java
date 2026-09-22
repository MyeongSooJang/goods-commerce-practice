package com.example.common.security.exception;

import com.example.common.exception.BusinessException;

public class AuthorizationDeniedException extends BusinessException {

    public AuthorizationDeniedException(String message) {
        super(message, 403);
    }
}
