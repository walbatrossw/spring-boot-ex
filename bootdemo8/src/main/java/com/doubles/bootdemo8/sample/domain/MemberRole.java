package com.doubles.bootdemo8.sample.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_member_roles")
@EqualsAndHashCode(of = "memberRoleNo")
@ToString
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberRoleNo;

    private String roleName;



}
