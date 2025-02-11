package org.example.idus.order;

import lombok.RequiredArgsConstructor;
import org.example.idus.member.model.Member;
import org.example.idus.order.model.OrderProduct;
import org.example.idus.order.model.OrderProductDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    @Transactional
    public void register(OrderProductDto.OrderRegister dto, Member member) {
        OrderProduct orderProduct = orderProductRepository.save(dto.toEntity(member));
    }
}
