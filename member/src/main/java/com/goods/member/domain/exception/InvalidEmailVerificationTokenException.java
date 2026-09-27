package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class InvalidEmailVerificationTokenException extends BusinessException {

    public InvalidEmailVerificationTokenException() {
        super("이메일 인증 토큰이 올바르지 않습니다.", 400);
    }
}
