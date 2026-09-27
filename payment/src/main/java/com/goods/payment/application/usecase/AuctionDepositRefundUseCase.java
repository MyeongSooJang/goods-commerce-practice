package com.goods.payment.application.usecase;

import java.util.UUID;

public interface AuctionDepositRefundUseCase {

    void refund(UUID bidId);
}
