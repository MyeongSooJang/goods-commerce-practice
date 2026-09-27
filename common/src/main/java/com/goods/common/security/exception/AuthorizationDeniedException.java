package com.goods.common.security.exception;

import com.goods.common.exception.BusinessException;

public class AuthorizationDeniedException extends BusinessException {

    public AuthorizationDeniedException(String message) {
        super(message, 403);
    }
}
