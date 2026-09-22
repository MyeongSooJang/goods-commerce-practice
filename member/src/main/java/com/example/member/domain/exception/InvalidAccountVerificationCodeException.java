package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class InvalidAccountVerificationCodeException extends BusinessException {

    public InvalidAccountVerificationCodeException() {
        super("계좌 인증 코드가 올바르지 않습니다.", 400);
    }

    public InvalidAccountVerificationCodeException(String message) {
        super(message, 400);
    }
}
