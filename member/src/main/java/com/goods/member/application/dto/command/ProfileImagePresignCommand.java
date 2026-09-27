package com.goods.member.application.dto.command;

public record ProfileImagePresignCommand(
        String fileName,
        String contentType
) {
}
