package org.example.idus.order;

import lombok.RequiredArgsConstructor;
import org.example.idus.member.model.Member;
import org.example.idus.order.model.OrderProduct;
import org.example.idus.order.model.OrderProductDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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
}
