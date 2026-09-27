package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class InvalidLoginException extends BusinessException {

    public InvalidLoginException() {
        super("이메일 또는 비밀번호가 올바르지 않습니다.", 401);
    }
}
