package com.doubles.bootboard.tutorials.member.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_members")
@EqualsAndHashCode(of = "memberId")
public class Member {

    @Id
    private String memberId;    // 회원 아이디
    private String memberPw;    // 회원 비밀번호
    private String memberName;  // 회원 이름

    @CreationTimestamp
    private Timestamp regDate;  // 회원 등록일자

    @UpdateTimestamp
    private Timestamp updateDate;   // 회원 수정일자

}
