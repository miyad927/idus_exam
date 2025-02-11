package org.example.idus.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.idus.member.model.Member;

import java.sql.Date;
import java.util.List;

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

    @AllArgsConstructor
    @Getter
    public static class ListResponse {
        private Boolean isSuccess;
        private List<OrderResponse> result;

        public static ListResponse success(List<OrderResponse> result) {
            return new ListResponse(true, result);
        }
    }

    @Getter
    public static class OrderResponse {

        private Long idx;
        private String orderNumber;
        private String name;
        private Date orderDate;
        private Long memberIdx;

        public static OrderResponse from(OrderProduct order) {
            OrderResponse response = new OrderResponse();
            response.idx = order.getIdx();
            response.orderNumber = order.getOrderNumber();
            response.name = order.getName();
            response.orderDate = order.getOrderDate();
            response.memberIdx = order.getMember().getIdx();
            return response;
        }
    }

}
