package com.example.practicaltest.spring.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {
    HANDMADE("제조 음료"),
    BOTTLE("병음료"),
    BAKERY("베이커리"),
    ;
    private final String text;
}
