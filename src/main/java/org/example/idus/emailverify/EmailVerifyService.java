package org.example.idus.emailverify;


import org.example.idus.emailverify.model.EmailVerify;
import org.example.idus.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmailVerifyService {
    private final JavaMailSender mailSender;
    private final EmailVerifyRepository emailVerifyRepository;

    public void sendEmail(String uuid, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[idus] 가입 이메일 인증");
        message.setText(
                "아래의 주소로 접속 하시면 최종 가입이 완료 됩니다."+ "\n" +
                "http://localhost:8080/member/verify?uuid=" + uuid
        );

        mailSender.send(message);
    }
    public void signup(Long idx, String email) {
        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()
                .idx(idx)
                .build();

        // 이메일 인증 정보 저장
        emailVerifyRepository.save(EmailVerify.builder()
                .member(member)
                .uuid(uuid)
                .build());

        // 이메일 전송
        sendEmail(uuid, email);
    }

    public Member verify(String uuid) {
        EmailVerify emailVerify = emailVerifyRepository.findByUuid(uuid).orElseThrow();

        return emailVerify.getMember();
    }
}
