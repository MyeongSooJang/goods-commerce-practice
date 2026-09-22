package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException() {
        super("회원을 찾을 수 없습니다.", 404);
    }
}
