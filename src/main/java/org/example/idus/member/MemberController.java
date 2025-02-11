package org.example.idus.member;

import lombok.RequiredArgsConstructor;
import org.example.idus.member.model.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/verify")
    public void verify(String uuid) {
        memberService.verify(uuid);
    }

    @PostMapping("/signup")
    public void signup(@RequestBody MemberDto.SignupRequest dto) {
        memberService.signup(dto);
    }

    @GetMapping("/{memberIdx}")
    public ResponseEntity<MemberDto.MemberResponse> read(@PathVariable Long memberIdx) {
        MemberDto.MemberResponse response = memberService.read(memberIdx);

        return ResponseEntity.ok(response);
    }
}
