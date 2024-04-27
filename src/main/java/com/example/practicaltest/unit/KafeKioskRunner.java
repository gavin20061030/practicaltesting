package com.example.practicaltest.unit;

import com.example.practicaltest.unit.beverage.Americano;
import com.example.practicaltest.unit.beverage.Latte;

public class KafeKioskRunner {
    public static void main(String[] args) {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        System.out.println(">>> 주문한 음료: 아메리카노");

        cafeKiosk.add(new Latte());
        System.out.println(">>> 주문한 음료: 라떼");

        int totalPrice = cafeKiosk.calculateTotalPrice();
        System.out.println(">>> 총 가격: " + totalPrice + "원");
    }
}
