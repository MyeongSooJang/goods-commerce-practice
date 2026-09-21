package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class RefreshTokenNotFoundException extends BusinessException {

    public RefreshTokenNotFoundException() {
        super("리프레시 토큰을 찾을 수 없습니다.", 401);
    }
}
