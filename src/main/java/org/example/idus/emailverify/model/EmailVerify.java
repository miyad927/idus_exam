package org.example.idus.emailverify.model;

import org.example.idus.member.model.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmailVerify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member member;
    private String uuid;
}
