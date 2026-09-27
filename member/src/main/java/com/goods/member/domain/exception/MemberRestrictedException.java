package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

import java.time.LocalDateTime;

public class MemberRestrictedException extends BusinessException {

    public MemberRestrictedException(LocalDateTime endAt) {
        super("회원은 " + endAt + "까지 이용이 제한됩니다.", 403);
    }
}
