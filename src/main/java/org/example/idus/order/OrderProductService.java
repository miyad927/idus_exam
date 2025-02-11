package org.example.idus.order;

import lombok.RequiredArgsConstructor;
import org.example.idus.member.model.Member;
import org.example.idus.member.model.MemberDto;
import org.example.idus.order.model.OrderProduct;
import org.example.idus.order.model.OrderProductDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    @Transactional
    public void register(OrderProductDto.OrderRegister dto, Member member) {

        dto.setOrderNumber(randomNumber("ORDER"));

        OrderProduct orderProduct = orderProductRepository.save(dto.toEntity(member));
    }

    public static String randomNumber(String prefix) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = now.format(formatter);
        String randomString = generateRandomString(5);

        return prefix + formattedDateTime + randomString;
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    public List<OrderProductDto.OrderResponse> list(@PathVariable Long memberIdx) {
        List<OrderProduct> feedList = orderProductRepository.findAllByMemberIdx(memberIdx);

        return feedList.stream().map(OrderProductDto.OrderResponse::from).collect(Collectors.toList());
    }

//    public OrderProductDto.OrderResponse listAll() {
//        OrderProduct order = orderProductRepository.findFirstByOrderByIdxDesc();
//        return OrderProductDto.OrderResponse.from(order);
//    }

    public List<OrderProductDto.OrderResponse> listAll() {
        List<OrderProduct> orderList = orderProductRepository.findByOrderByIdxDesc();

        return orderList.stream().map(OrderProductDto.OrderResponse::from).collect(Collectors.toList());
    }
}
