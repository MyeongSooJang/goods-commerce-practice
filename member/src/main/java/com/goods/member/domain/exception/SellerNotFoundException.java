package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class SellerNotFoundException extends BusinessException {

    public SellerNotFoundException() {
        super("판매자 정보를 찾을 수 없습니다.", 404);
    }
}
