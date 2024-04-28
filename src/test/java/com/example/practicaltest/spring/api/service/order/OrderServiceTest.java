package com.example.practicaltest.spring.api.service.order;

import com.example.practicaltest.spring.api.controller.order.request.OrderCreateRequest;
import com.example.practicaltest.spring.api.service.order.response.OrderResponse;
import com.example.practicaltest.spring.domain.product.Product;
import com.example.practicaltest.spring.domain.product.ProductRepository;
import com.example.practicaltest.spring.domain.product.ProductSellingStatus;
import com.example.practicaltest.spring.domain.product.ProductType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;


    @DisplayName("주문번호 리스트를 받아 주문을 생성 한다.")
    @Test
    void test() {
        //given
        Product product01 = createProduct(ProductType.HANDMADE, "001", 1000);
        Product product02 = createProduct(ProductType.HANDMADE, "002", 3000);
        Product product03 = createProduct(ProductType.HANDMADE, "003", 5000);
        productRepository.saveAll(List.of(product01, product02, product03));

        OrderCreateRequest request = OrderCreateRequest.builder()
                .productNumbers(List.of("001", "002"))
                .build();
        //when
        OrderResponse orderResponse = orderService.createOrder(request);
        //then
        assertThat(orderResponse.getId()).isNotNull();
        assertThat(orderResponse).extracting("registerDateTime", "totalPrice")
                .contains(LocalDateTime.now(), 4000);
        assertThat(orderResponse.getProducts()).hasSize(2)
                .extracting("productNumber", "price")
                .containsExactlyInAnyOrder(
                        tuple("001", 1000),
                        tuple("002", 3000)
                );
    }

    private Product createProduct(ProductType type, String productNumber, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("메뉴 이름")
                .price(price)
                .build();
    }
}