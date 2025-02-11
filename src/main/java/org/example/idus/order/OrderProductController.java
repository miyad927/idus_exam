package org.example.idus.order;

import lombok.RequiredArgsConstructor;
import org.example.idus.member.model.Member;
import org.example.idus.order.model.OrderProductDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderProductController {
    private final OrderProductService orderProductService;

    @PostMapping("/register")
    public void register(@AuthenticationPrincipal Member member, @RequestBody OrderProductDto.OrderRegister dto) {
        orderProductService.register(dto, member);
    }
}
