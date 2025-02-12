package org.example.idus.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.idus.member.model.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name="회원 기능")
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public void signup(@RequestBody MemberDto.SignupRequest dto) {
        memberService.signup(dto);
    }

    @Operation(summary = "단일 회원 조회")
    @GetMapping("/{memberIdx}")
    public ResponseEntity<MemberDto.MemberResponse> read(@PathVariable Long memberIdx) {
        MemberDto.MemberResponse response = memberService.read(memberIdx);

        return ResponseEntity.ok(response);
    }
}
