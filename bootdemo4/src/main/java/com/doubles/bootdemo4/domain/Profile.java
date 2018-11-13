package com.doubles.bootdemo4.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = "member")
@Entity
@Table(name = "tbl_profile")
@EqualsAndHashCode(of = "fname")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    private String fname;

    private boolean current;

    @ManyToOne // 다대일 연관관계
    private Member member;
}
