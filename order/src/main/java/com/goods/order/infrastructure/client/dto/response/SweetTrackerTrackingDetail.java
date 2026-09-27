package com.goods.order.infrastructure.client.dto.response;

public record SweetTrackerTrackingDetail(
        String timeString,
        String where,
        String kind
) {
}