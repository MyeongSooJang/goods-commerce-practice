package com.goods.payment.application.service;

import com.goods.payment.application.dto.OrderPaymentValidationCommand;
import com.goods.payment.application.usecase.OrderPaymentValidationUseCase;
import com.goods.payment.common.exception.InvalidCardPaymentRequestException;
import com.goods.payment.domain.service.OrderPaymentValidationData;
import com.goods.payment.domain.service.OrderPaymentValidationGateway;
import org.springframework.stereotype.Service;

@Service
public class OrderPaymentValidationService implements OrderPaymentValidationUseCase {

    private final OrderPaymentValidationGateway orderPaymentValidationGateway;

    public OrderPaymentValidationService(OrderPaymentValidationGateway orderPaymentValidationGateway) {
        this.orderPaymentValidationGateway = orderPaymentValidationGateway;
    }

    @Override
    public OrderPaymentValidationData validateOrderPayment(OrderPaymentValidationCommand command) {
        validateCommand(command);
        return orderPaymentValidationGateway.validate(command.orderId(), command.buyerId(), command.amount());
    }

    private void validateCommand(OrderPaymentValidationCommand command) {
        if (command == null) {
            throw new InvalidCardPaymentRequestException("주문 결제 검증 요청이 필요합니다.");
        }
        if (command.orderId() == null) {
            throw new InvalidCardPaymentRequestException("주문 결제 검증을 위한 주문 ID는 필수입니다.");
        }
        if (command.buyerId() == null) {
            throw new InvalidCardPaymentRequestException("주문 결제 검증을 위한 구매자 ID는 필수입니다.");
        }
        if (command.amount() == null || command.amount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new InvalidCardPaymentRequestException("주문 결제 검증 금액은 0보다 커야 합니다.");
        }
    }
}
