package org.example.idus.order;

import lombok.RequiredArgsConstructor;
import org.example.idus.member.model.Member;
import org.example.idus.order.model.OrderProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderProductController {
    private final OrderProductService orderProductService;

    /*
    * 주문 등록
    * */
    @PostMapping("/register")
    public void register(@AuthenticationPrincipal Member member, @RequestBody OrderProductDto.OrderRegister dto) {
        orderProductService.register(dto, member);
    }

    /*
    * 단일 회원의 주문 목록 조회
    * */
    @GetMapping("/list/{memberIdx}")
    public ResponseEntity<OrderProductDto.ListResponse> list(@PathVariable Long memberIdx) {
        List<OrderProductDto.OrderResponse> result = orderProductService.list(memberIdx);

        return ResponseEntity.ok(OrderProductDto.ListResponse.success(result));
    }


}
