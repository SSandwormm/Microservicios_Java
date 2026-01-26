package com.microservice.order.dto;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;
    private String productName;
    private Double price;
    private Integer stock;
}
