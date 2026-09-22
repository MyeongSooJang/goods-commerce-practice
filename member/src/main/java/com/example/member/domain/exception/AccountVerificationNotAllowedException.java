package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class AccountVerificationNotAllowedException extends BusinessException {

    public AccountVerificationNotAllowedException() {
        super("계좌 인증을 진행할 수 없습니다.", 409);
    }

    public AccountVerificationNotAllowedException(String message) {
        super(message, 409);
    }
}
