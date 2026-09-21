package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class InvalidEmailVerificationTokenException extends BusinessException {

    public InvalidEmailVerificationTokenException() {
        super("이메일 인증 토큰이 올바르지 않습니다.", 400);
    }
}
