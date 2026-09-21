package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class AccountVerificationNotFoundException extends BusinessException {

    public AccountVerificationNotFoundException() {
        super("계좌 인증 세션을 찾을 수 없습니다.", 404);
    }

    public AccountVerificationNotFoundException(String message) {
        super(message, 404);
    }
}
