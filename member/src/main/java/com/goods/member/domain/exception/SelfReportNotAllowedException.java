package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class SelfReportNotAllowedException extends BusinessException {

    public SelfReportNotAllowedException() {
        super("자기 자신은 신고할 수 없습니다.", 400);
    }
}
