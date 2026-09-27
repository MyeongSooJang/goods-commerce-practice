package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class EmailVerificationRequiredException extends BusinessException {

    public EmailVerificationRequiredException() {
        super("이메일 인증이 필요합니다.", 403);
    }
}
