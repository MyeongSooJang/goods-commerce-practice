package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class InvalidPasswordResetTokenException extends BusinessException {

    public InvalidPasswordResetTokenException() {
        super("유효하지 않거나 만료된 비밀번호 재설정 링크입니다.", 400);
    }
}
