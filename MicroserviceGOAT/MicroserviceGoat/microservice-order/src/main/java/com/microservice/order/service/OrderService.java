package com.microservice.order.service;

import com.microservice.order.client.CartClient;
import com.microservice.order.client.ProductClient;
import com.microservice.order.dto.*;
import com.microservice.order.entity.Order;
import com.microservice.order.entity.OrderItem;
import com.microservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartClient cartClient;
    private final ProductClient productClient;

    @Transactional
    public OrderResponse createOrder(Long userId) {

        List<CartItemResponse> cartItems = cartClient.getCart(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        final double[] total = {0};

        Order order = Order.builder()
                .userId(userId)
                .status("CREATED")
                .createdAt(LocalDateTime.now())
                .build();

        List<OrderItem> items = cartItems.stream().map(item -> {

            ProductResponse product = productClient.getById(item.getProductId());

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente: " + product.getProductName());
            }

            productClient.reduceStock(product.getId(), item.getQuantity());

            total[0] += item.getPrice() * item.getQuantity();

            return OrderItem.builder()
                    .productId(item.getProductId())
                    .productName(item.getProductName())
                    .price(item.getPrice())
                    .quantity(item.getQuantity())
                    .order(order)
                    .build();
        }).toList();

        order.setItems(items);
        order.setTotal(total[0]);

        Order saved = orderRepository.save(order);

        cartClient.clearCart(userId);

        return OrderResponse.builder()
                .orderId(saved.getId())
                .userId(saved.getUserId())
                .status(saved.getStatus())
                .total(saved.getTotal())
                .createdAt(saved.getCreatedAt())
                .items(items.stream().map(i ->
                        OrderItemResponse.builder()
                                .productId(i.getProductId())
                                .productName(i.getProductName())
                                .price(i.getPrice())
                                .quantity(i.getQuantity())
                                .build()
                ).toList())
                .build();
    }

    public List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
