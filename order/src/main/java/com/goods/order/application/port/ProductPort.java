package com.goods.order.application.port;

import com.goods.order.application.port.dto.request.ProductStockDeductRequest;
import com.goods.order.application.port.dto.response.ProductInfo;

import java.util.List;

public interface ProductPort {

    List<ProductInfo> deductStock(List<ProductStockDeductRequest> requests);
}
