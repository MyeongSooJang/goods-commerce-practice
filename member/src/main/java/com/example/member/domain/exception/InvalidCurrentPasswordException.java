package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class InvalidCurrentPasswordException extends BusinessException {

    public InvalidCurrentPasswordException() {
        super("현재 비밀번호가 올바르지 않습니다.", 400);
    }
}
