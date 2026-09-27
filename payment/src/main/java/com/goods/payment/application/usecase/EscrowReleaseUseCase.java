package com.goods.payment.application.usecase;

import com.goods.payment.application.dto.EscrowReleaseCommand;
import com.goods.payment.application.dto.EscrowReleaseResult;

/**
 * escrow release 유스케이스의 진입점이다.
 */
public interface EscrowReleaseUseCase {

    EscrowReleaseResult releaseEscrow(EscrowReleaseCommand command);
}
