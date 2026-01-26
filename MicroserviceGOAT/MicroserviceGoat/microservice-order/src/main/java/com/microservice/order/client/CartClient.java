package com.microservice.order.client;

import com.microservice.order.dto.CartItemResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-cart")
public interface CartClient {

    @GetMapping("/api/cart/{userId}")
    List<CartItemResponse> getCart(@PathVariable("userId") Long userId);

    @DeleteMapping("/api/cart/clear/{userId}")
    void clearCart(@PathVariable("userId") Long userId);
}
