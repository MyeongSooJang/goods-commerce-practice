package com.goods.order.domain.repository;

import com.goods.order.domain.entity.Claim;

import java.util.List;

public interface ClaimRepository {

    List<Claim> saveAll(List<Claim> claims);
}
