package com.goods.order.application.usecase;

import com.goods.order.presentation.dto.request.ReturnInspectRequest;
import com.goods.order.presentation.dto.response.ReturnInspectResponse;

import java.util.UUID;

public interface ReturnInspectUseCase {

    ReturnInspectResponse inspect(UUID returnRequestId, UUID sellerMemberId, ReturnInspectRequest request);
}
