package com.goods.order.application.service;

import com.goods.order.application.port.TrackingPort;
import com.goods.order.application.usecase.DeliveryTrackingUseCase;
import com.goods.order.common.exception.CustomException;
import com.goods.order.common.exception.ErrorCode;
import com.goods.order.domain.entity.Delivery;
import com.goods.order.domain.repository.DeliveryRepository;
import com.goods.order.presentation.dto.response.DeliveryTrackingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeliveryTrackingService implements DeliveryTrackingUseCase {

    private final TrackingPort trackingPort;
    private final DeliveryRepository deliveryRepository;

    @Override
    public DeliveryTrackingResponse getTrackingInfo(UUID deliveryId, UUID memberId) {
        Delivery delivery = deliveryRepository.findByDeliveryIdAndBuyerId(deliveryId, memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.DELIVERY_NOT_FOUND));

        return trackingPort.getTrackingInfo(delivery.getCourierCode(), delivery.getInvoiceNumber());
    }
}
