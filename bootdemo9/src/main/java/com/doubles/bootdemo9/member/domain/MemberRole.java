package com.doubles.bootdemo9.member.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_member_roles")
@EqualsAndHashCode(of = "memberRoleId")
@ToString
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberRoleId;

    private String roleName;

}
