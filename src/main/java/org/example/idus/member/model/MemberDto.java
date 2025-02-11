package org.example.idus.member.model;

import lombok.Getter;

public class MemberDto {
    @Getter
    public static class SignupRequest {
        private String name;
        private String nickName;
        private String password;
        private int phoneNumber;
        private String email;
        private String gender;

        public Member toEntity(String encodedPassword) {
            return Member.builder()
                    .name(name)
                    .nickName(nickName)
                    .password(encodedPassword)
                    .phoneNumber(phoneNumber)
                    .email(email)
                    .gender(gender)
                    .role("ROLE_USER")
                    .build();
        }
    }
}
