package com.example.member.domain.exception;

import com.example.common.exception.BusinessException;

public class SelfReportNotAllowedException extends BusinessException {

    public SelfReportNotAllowedException() {
        super("자기 자신은 신고할 수 없습니다.", 400);
    }
}
