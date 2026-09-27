package com.goods.payment.application.usecase;

import com.goods.payment.application.dto.AuctionDepositCommand;
import com.goods.payment.application.dto.AuctionDepositResult;

public interface AuctionDepositUseCase {

    AuctionDepositResult processAuctionDeposit(AuctionDepositCommand command);
}
