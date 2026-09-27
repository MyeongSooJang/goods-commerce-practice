package com.goods.auction.application.port;

import com.goods.auction.application.port.dto.request.BidFeeChargeRequest;
import com.goods.auction.application.port.dto.response.BidFeeChargeResponse;

public interface BidFeeChargePort {

    BidFeeChargeResponse chargeBidFee(BidFeeChargeRequest request);
}
