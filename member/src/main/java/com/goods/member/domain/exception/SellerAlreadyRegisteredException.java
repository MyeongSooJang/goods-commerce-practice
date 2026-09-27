package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class SellerAlreadyRegisteredException extends BusinessException {

    public SellerAlreadyRegisteredException() {
        super("이미 판매자로 등록된 회원입니다.", 409);
    }
}
