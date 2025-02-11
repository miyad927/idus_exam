package org.example.idus.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MemberResponse {
        private Long idx;
        private String name;
        private String nickName;
        private int phoneNumber;
        private String email;
        private String gender;

        public static MemberResponse from(Member member) {
            return MemberResponse.builder()
                    .idx(member.getIdx())
                    .name(member.getName())
                    .nickName(member.getNickName())
                    .phoneNumber(member.getPhoneNumber())
                    .email(member.getEmail())
                    .gender(member.getGender())
                    .build();
        }

    }
}
