package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class MemberReportNotFoundException extends BusinessException {

    public MemberReportNotFoundException() {
        super("회원 신고를 찾을 수 없습니다.", 404);
    }
}
