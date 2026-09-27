package com.goods.ai.application.usecase;

import com.goods.ai.application.dto.AuctionPriceRecommendationCommand;
import com.goods.ai.application.dto.AuctionPriceRecommendationResult;

public interface AuctionPriceRecommendationUseCase {

    AuctionPriceRecommendationResult recommend(AuctionPriceRecommendationCommand command);
}

