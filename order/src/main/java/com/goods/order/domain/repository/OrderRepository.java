package com.goods.order.domain.repository;

import com.goods.order.domain.entity.Order;
import com.goods.order.domain.enumtype.OrderStatus;
import com.goods.order.domain.enumtype.OrderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    Order save(Order order);

    Page<Order> findByBuyerIdAndOrderType(UUID buyerId, OrderType orderType, String keyword, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Optional<Order> findByOrderIdAndBuyerId(UUID orderId, UUID buyerId);

    Optional<Order> findByOrderId(UUID orderId);

    List<Order> findByStatusAndDeliveredAtBefore(OrderStatus status, LocalDateTime threshold);

    boolean existsByOrderNumber(String orderNumber);

    Optional<Order> findByAuctionId(UUID auctionId);
}
