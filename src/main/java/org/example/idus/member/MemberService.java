package org.example.idus.member;

import lombok.RequiredArgsConstructor;
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

    @Transactional
    public void signup(MemberDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        System.out.println(encodedPassword);

        Member member = memberRepository.save(dto.toEntity(encodedPassword));
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

    @Transactional(readOnly = true)
    public MemberDto.MemberResponse read(Long memberIdx) {
        Member member = memberRepository.findById(memberIdx).orElseThrow();
        return MemberDto.MemberResponse.from(member);
    }
}