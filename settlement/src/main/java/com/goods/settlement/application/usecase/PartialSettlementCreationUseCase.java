package com.goods.settlement.application.usecase;

import com.goods.settlement.application.dto.PartialSettlementCreateCommand;
import com.goods.settlement.application.dto.PartialSettlementCreateResult;

/**
 * 판매자 부분 정산 생성 유스케이스 진입점이다.
 */
public interface PartialSettlementCreationUseCase {

    PartialSettlementCreateResult createPartialSettlement(PartialSettlementCreateCommand command);
}
