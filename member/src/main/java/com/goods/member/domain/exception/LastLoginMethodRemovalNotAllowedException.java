package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class LastLoginMethodRemovalNotAllowedException extends BusinessException {

    public LastLoginMethodRemovalNotAllowedException() {
        super("마지막 로그인 수단은 해제할 수 없습니다.", 409);
    }
}
