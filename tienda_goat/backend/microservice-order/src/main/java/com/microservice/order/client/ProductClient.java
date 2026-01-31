package com.microservice.order.client;

import com.microservice.order.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "microservice-product")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getById(@PathVariable("id") Long id);

    @PutMapping("/api/products/{id}/reduce-stock/{quantity}")
    void reduceStock(
            @PathVariable("id") Long id,
            @PathVariable("quantity") Integer quantity
    );
}
