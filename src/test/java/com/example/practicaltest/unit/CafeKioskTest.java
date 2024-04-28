package com.example.practicaltest.unit;

import com.example.practicaltest.unit.beverage.Americano;
import com.example.practicaltest.unit.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CafeKioskTest {

    @Test
    @DisplayName("수동으로 음료를 추가하면 음료수가 생성 된다.")
    void add_manual_test() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        System.out.println(">>> 주문한 음료 수:" + cafeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료 : " + cafeKiosk.getBeverages().get(0).getName());
    }

    @Test
    @DisplayName("음료 추가")
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    @DisplayName("여러 음료 추가")
    void addSeveralBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano,2);

        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
    }

    @Test
    @DisplayName("음료 추가시 0 추가 실패")
    void addZeroBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        assertThatThrownBy(()-> cafeKiosk.add(americano,0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료의 수량은 1개 이상이어야 합니다.");
    }

    @Test
    @DisplayName("음료 추가시 음료가 없을 때 실패")
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);
        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);

        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @Test
    @DisplayName("음료 추가시 음료가 없을 때 실패")
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @Test
    @DisplayName("음료를 주문 하면 주문이 생성 된다.")
    void createOrder() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        Order order = cafeKiosk.createOrder();

        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @Test
    @DisplayName("영업 시작 시간 이전에는 주문을 생성 할 수 없다.")
    void createOrderOuterTest() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2024,4,28,9,00)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("주문 가능 시간이 아닙니다.");
    }

}
