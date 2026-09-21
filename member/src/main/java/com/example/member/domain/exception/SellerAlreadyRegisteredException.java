package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class SellerAlreadyRegisteredException extends BusinessException {

    public SellerAlreadyRegisteredException() {
        super("이미 판매자로 등록된 회원입니다.", 409);
    }
}
