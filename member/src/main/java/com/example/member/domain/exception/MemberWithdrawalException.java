package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberWithdrawalException extends BusinessException {

    private final String code;

    public MemberWithdrawalException(String code, HttpStatus status, String message) {
        super(message, status.value());
        this.code = code;
    }
}
