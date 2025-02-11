package org.example.idus.order.model;

import lombok.Getter;
import org.example.idus.member.model.Member;

import java.sql.Date;

public class OrderProductDto {
    @Getter
    public static class OrderRegister {
        private String name;
        private Date orderDate;

        public OrderProduct toEntity(Member member) {
            return OrderProduct.builder()
                    .name(name)
                    .orderDate(orderDate)
                    .member(member)
                    .build();
        }
    }

}
