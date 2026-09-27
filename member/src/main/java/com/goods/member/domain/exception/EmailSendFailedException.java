package com.goods.member.domain.exception;

import com.goods.common.exception.BusinessException;

public class EmailSendFailedException extends BusinessException {

    public EmailSendFailedException() {
        super("이메일 전송에 실패했습니다.", 503);
    }

    public EmailSendFailedException(String message) {
        super(message, 503);
    }

    public EmailSendFailedException(String message, Throwable cause) {
        super(message, 503);
        initCause(cause);
    }
}
