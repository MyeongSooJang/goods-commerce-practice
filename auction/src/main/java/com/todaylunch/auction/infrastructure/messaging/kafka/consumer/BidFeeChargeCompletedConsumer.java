package com.todaylunch.auction.infrastructure.messaging.kafka.consumer;

import com.todaylunch.auction.application.service.BidUpdateService;
import com.todaylunch.auction.common.exception.application.BidNotFoundException;
import com.todaylunch.auction.domain.entity.Bid;
import com.todaylunch.auction.domain.enumtype.BidStatus;
import com.todaylunch.auction.domain.repository.BidRepository;
import com.todaylunch.auction.infrastructure.messaging.kafka.KafkaTopics;
import com.todaylunch.auction.infrastructure.messaging.kafka.message.BidFeeChargeCompletedMessage;
import com.todaylunch.common.event.contract.EventEnvelope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class BidFeeChargeCompletedConsumer {

    private final BidRepository bidRepository;
    private final BidUpdateService bidUpdateService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = KafkaTopics.BID_FEE_CHARGE_COMPLETED, containerFactory = "bidFeeChargeResultKafkaListenerContainerFactory")
    public void handle(String payload) throws Exception {
        EventEnvelope<BidFeeChargeCompletedMessage> envelope
                = objectMapper.readValue(payload, new TypeReference<>() {});
        BidFeeChargeCompletedMessage message = envelope.payload();

        Bid bid = bidRepository.findById(message.bidId())
                .orElseThrow(BidNotFoundException::new);

        if (bid.getStatus() != BidStatus.PENDING) {
            log.warn("중복 이벤트 또는 잘못된 상태 — 무시: bidId={}, status={}",
                    bid.getBidId(), bid.getStatus());
            return;
        }

        try {
            bidUpdateService.activate(message.bidId());
            log.info("Bid confirmed: bidId={}, auctionId={}", message.bidId(), message.auctionId());
        } catch (ObjectOptimisticLockingFailureException e) {
            log.warn("낙관락 재시도 소진 — 입찰 취소: bidId={}", message.bidId());
            bidUpdateService.cancel(message.bidId());
        }
    }
}
