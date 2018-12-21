package com.doubles.bootdemo9.member.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_members")
@EqualsAndHashCode(of = "memberEmail")
@ToString
public class Member {

    @Id
    private String memberEmail;

    private String memberPw;

    private String memberName;

    @CreationTimestamp
    private Timestamp regDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member")
    private List<MemberRole> roles;

}
