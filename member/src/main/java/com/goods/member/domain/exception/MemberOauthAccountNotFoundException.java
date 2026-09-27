package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class MemberOauthAccountNotFoundException extends BusinessException {

    public MemberOauthAccountNotFoundException() {
        super("연동된 외부 계정을 찾을 수 없습니다.", 404);
    }
}
