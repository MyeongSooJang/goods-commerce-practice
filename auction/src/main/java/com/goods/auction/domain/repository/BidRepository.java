package com.goods.auction.domain.repository;

import com.goods.auction.domain.entity.Bid;
import com.goods.auction.domain.enumtype.BidStatus;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BidRepository {

    Bid save(Bid bid);

    Optional<Bid> findById(UUID bidId);

    Optional<Bid> findActiveByAuctionId(UUID auctionId);

    Optional<Bid> findCurrentValidByAuctionId(UUID auctionId);

    Page<Bid> findAllByAuctionId(UUID auctionId, Pageable pageable);

    Map<BidStatus, Long> countByStatusForAuction(UUID auctionId);
}
