package com.microservice.cart.controller;

import com.microservice.cart.dto.AddCartItemRequest;
import com.microservice.cart.entity.CartItem;
import com.microservice.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> add(@RequestBody AddCartItemRequest request) {
        return ResponseEntity.ok(cartService.addProduct(request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @GetMapping("/total/{userId}")
    public ResponseEntity<Double> getTotal(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getTotal(userId));
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        cartService.removeItem(id);
        return ResponseEntity.ok("Producto eliminado");
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clear(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Carrito vaciado");
    }
}

