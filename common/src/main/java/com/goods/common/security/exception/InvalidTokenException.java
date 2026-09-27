package com.goods.common.security.exception;

import com.goods.common.exception.BusinessException;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException() {
        super("Invalid token.", 401);
    }
}
