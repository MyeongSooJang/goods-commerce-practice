package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class AccountVerificationResendLimitExceededException extends BusinessException {

    public AccountVerificationResendLimitExceededException() {
        super("계좌 인증 재전송 횟수를 초과했습니다.", 429);
    }

    public AccountVerificationResendLimitExceededException(String message) {
        super(message, 429);
    }
}
