package com.goods.member.infrastructure.email;

public interface EmailSender {

    void send(String to, String subject, String body, boolean html);
}
