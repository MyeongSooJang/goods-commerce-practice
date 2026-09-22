package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class AccountVerificationAttemptLimitExceededException extends BusinessException {

    public AccountVerificationAttemptLimitExceededException() {
        super("계좌 인증 시도 횟수를 초과했습니다.", 409);
    }

    public AccountVerificationAttemptLimitExceededException(String message) {
        super(message, 409);
    }
}
