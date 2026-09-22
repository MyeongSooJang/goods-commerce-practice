package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class MemberWithdrawnException extends BusinessException {

    public MemberWithdrawnException() {
        super("탈퇴한 계정입니다. 같은 이메일로 다시 가입해 주세요.", 403);
    }
}
