package com.microservice.cart.service;

import com.microservice.cart.client.ProductClient;
import com.microservice.cart.dto.AddCartItemRequest;
import com.microservice.cart.dto.ProductResponse;
import com.microservice.cart.entity.CartItem;
import com.microservice.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public String addProduct(AddCartItemRequest request) {

        ProductResponse product = productClient.getById(request.getProductId());

        if (product.getStock() < request.getQuantity()) {
            throw new RuntimeException("Stock insuficiente");
        }

        CartItem item = cartRepository
                .findByUserIdAndProductId(request.getUserId(), request.getProductId())
                .orElse(null);

        if (item == null) {
            item = CartItem.builder()
                    .userId(request.getUserId())
                    .productId(product.getId())
                    .productName(product.getProductName())
                    .price(product.getPrice())
                    .quantity(request.getQuantity())
                    .build();
        } else {
            item.setQuantity(item.getQuantity() + request.getQuantity());
        }

        cartRepository.save(item);

        return "Producto agregado correctamente";
    }

    public List<CartItem> getCart(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Double getTotal(Long userId) {
        return cartRepository.findByUserId(userId)
                .stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }

    public void removeItem(Long id) {
        cartRepository.deleteById(id);
    }

    public void clearCart(Long userId) {

        List<CartItem> items = cartRepository.findByUserId(userId);

        if (items.isEmpty()) {
            throw new RuntimeException("El carrito ya está vacío");
        }

        cartRepository.deleteAll(items);
    }


}
