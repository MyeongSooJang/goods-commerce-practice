package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class InvalidEmailVerificationAutoLoginTokenException extends BusinessException {

    public InvalidEmailVerificationAutoLoginTokenException() {
        super("이메일 인증 자동 로그인 토큰이 유효하지 않거나 만료되었습니다.", 401);
    }
}
