package com.goods.member.application.dto.command;

import com.goods.common.security.auth.enumtype.MemberRole;

public record CreateMemberCommand(
        String email,
        String password,
        String nickname,
        String phone,
        String address,
        String profileImageKey,
        MemberRole role,
        String kakaoLinkToken
) {
}
