package com.microservice.cart.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemRequest {

    private Long userId;
    private Long productId;
    private Integer quantity;
}
