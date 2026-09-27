package com.goods.order.application.usecase;

import com.goods.order.domain.enumtype.ReturnRequestStatus;
import com.goods.order.presentation.dto.response.ReturnRequestSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReturnRequestSearchUseCase {

    Page<ReturnRequestSummaryResponse> findForSeller(UUID sellerMemberId, ReturnRequestStatus status, Pageable pageable);
}
