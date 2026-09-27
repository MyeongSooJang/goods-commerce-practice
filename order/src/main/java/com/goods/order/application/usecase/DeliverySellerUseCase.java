package com.goods.order.application.usecase;

import com.goods.order.domain.enumtype.DeliveryStatus;
import com.goods.order.presentation.dto.response.DeliveryStatusCountResponse;
import com.goods.order.presentation.dto.response.SellerDeliveryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

public interface DeliverySellerUseCase {

    Page<SellerDeliveryResponse> getDeliveries(
            UUID sellerId,
            DeliveryStatus status,
            String orderNumber,
            String receiver,
            String productName,
            String courierName,
            LocalDate dateFrom,
            LocalDate dateTo,
            Pageable pageable
    );

    DeliveryStatusCountResponse getStatusCounts(UUID sellerId);
}
