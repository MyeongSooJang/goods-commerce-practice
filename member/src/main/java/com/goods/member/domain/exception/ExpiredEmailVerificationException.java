package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class ExpiredEmailVerificationException extends BusinessException {

    public ExpiredEmailVerificationException() {
        super("이메일 인증 토큰이 만료되었습니다.", 410);
    }
}
