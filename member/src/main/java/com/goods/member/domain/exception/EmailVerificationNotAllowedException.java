package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class EmailVerificationNotAllowedException extends BusinessException {

    public EmailVerificationNotAllowedException() {
        super("이메일 인증을 진행할 수 없습니다.", 409);
    }

    public EmailVerificationNotAllowedException(String message) {
        super(message, 409);
    }
}
