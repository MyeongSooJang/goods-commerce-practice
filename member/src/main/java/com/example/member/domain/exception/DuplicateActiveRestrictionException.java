package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class DuplicateActiveRestrictionException extends BusinessException {

    public DuplicateActiveRestrictionException() {
        super("해당 회원에게 같은 유형의 활성 제재가 이미 존재합니다.", 409);
    }
}
