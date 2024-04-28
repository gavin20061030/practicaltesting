package com.example.practicaltest.spring.api.service.order;

import com.example.practicaltest.spring.api.controller.order.request.OrderCreateRequest;
import com.example.practicaltest.spring.api.service.order.response.OrderResponse;
import com.example.practicaltest.spring.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;

    public OrderResponse createOrder(OrderCreateRequest request) {
        List<String> productNumbers = request.getProductNumbers();
        return null;
    }
}
