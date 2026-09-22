package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class DuplicateMemberEmailException extends BusinessException {

    public DuplicateMemberEmailException() {
        super("이미 사용 중인 이메일입니다.", 409);
    }
}
