package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class DuplicateMemberReportException extends BusinessException {

    public DuplicateMemberReportException() {
        super("같은 신고자가 접수한 대기 중인 회원 신고가 이미 존재합니다.", 409);
    }
}
