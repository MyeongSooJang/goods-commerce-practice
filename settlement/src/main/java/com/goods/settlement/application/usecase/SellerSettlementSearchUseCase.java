package com.goods.settlement.application.usecase;

import com.goods.settlement.application.dto.PagedResult;
import com.goods.settlement.application.dto.SellerSettlementDetailResult;
import com.goods.settlement.application.dto.SellerSettlementListItemResult;
import com.goods.settlement.domain.enumtype.SettlementStatus;
import com.goods.settlement.domain.enumtype.SettlementType;
import java.util.UUID;

public interface SellerSettlementSearchUseCase {

    PagedResult<SellerSettlementListItemResult> findSettlements(
            UUID sellerId,
            SettlementType settlementType,
            SettlementStatus settlementStatus,
            Integer settlementYear,
            Integer settlementMonth,
            int page,
            int size
    );

    SellerSettlementDetailResult findSettlementDetail(UUID sellerId, UUID settlementId);
}
