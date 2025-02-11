package org.example.idus.member;

import lombok.RequiredArgsConstructor;
import org.example.idus.emailverify.EmailVerifyService;
import org.example.idus.member.model.Member;
import org.example.idus.member.model.MemberDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerifyService emailVerifyService;

    @Transactional
    public void signup(MemberDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        System.out.println(encodedPassword);

        Member member = memberRepository.save(dto.toEntity(encodedPassword));

        emailVerifyService.signup(member.getIdx(), member.getEmail());

    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findByEmail(username);

        if (result.isPresent()) {
            Member member = result.get();
            return member;
        }

        return null;
    }

    @Transactional
    public void verify(String uuid) {
        Member member = emailVerifyService.verify(uuid);
        if(member != null) {
            member.verify();
            memberRepository.save(member);
        }
    }
}