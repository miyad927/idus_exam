package org.example.idus.order.model;

import lombok.Getter;
import org.example.idus.member.model.Member;

import java.sql.Date;

public class OrderProductDto {
    @Getter
    public static class OrderRegister {
        private String orderNumber;
        private String name;
        private Date orderDate;

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public OrderProduct toEntity(Member member) {
            return OrderProduct.builder()
                    .orderNumber(orderNumber)
                    .name(name)
                    .orderDate(orderDate)
                    .member(member)
                    .build();
        }
    }

}
