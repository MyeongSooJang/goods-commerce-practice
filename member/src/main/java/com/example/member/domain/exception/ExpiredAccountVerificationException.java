package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class ExpiredAccountVerificationException extends BusinessException {

    public ExpiredAccountVerificationException() {
        super("계좌 인증 세션이 만료되었습니다.", 410);
    }

    public ExpiredAccountVerificationException(String message) {
        super(message, 410);
    }
}
