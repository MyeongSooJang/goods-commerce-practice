package com.goods.order.domain.enumtype;

import com.goods.order.common.exception.CustomException;
import com.goods.order.common.exception.ErrorCode;

public enum PaymentStatus {
    SUCCESS,
    FAILED;

    public static PaymentStatus from(String value) {
        try {
            return PaymentStatus.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new CustomException(ErrorCode.INVALID_PAYMENT_STATUS);
        }
    }
}
