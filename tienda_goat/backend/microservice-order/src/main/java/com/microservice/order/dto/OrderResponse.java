package com.microservice.order.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    private Long orderId;
    private Long userId;
    private Double total;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;
}
