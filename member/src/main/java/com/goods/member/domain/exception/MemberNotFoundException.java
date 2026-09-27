package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class MemberNotFoundException extends BusinessException {

    public MemberNotFoundException() {
        super("회원을 찾을 수 없습니다.", 404);
    }
}
