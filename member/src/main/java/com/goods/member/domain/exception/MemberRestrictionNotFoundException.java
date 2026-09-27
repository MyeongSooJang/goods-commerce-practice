package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class MemberRestrictionNotFoundException extends BusinessException {

    public MemberRestrictionNotFoundException() {
        super("회원 제재 내역을 찾을 수 없습니다.", 404);
    }
}
