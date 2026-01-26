package com.microservice.cart.client;

import com.microservice.cart.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-product")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponse getById(@PathVariable Long id);
}
