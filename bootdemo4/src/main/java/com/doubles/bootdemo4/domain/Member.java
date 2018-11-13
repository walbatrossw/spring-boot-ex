package com.doubles.bootdemo4.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_members")
@EqualsAndHashCode(of = "mid")
public class Member {

    @Id
    private String mid;
    private String mpw;
    private String mname;


}
