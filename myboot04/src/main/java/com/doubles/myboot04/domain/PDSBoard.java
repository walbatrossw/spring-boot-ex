package com.doubles.myboot04.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "files")
@Entity
@Table(name = "tbl_pds")
@EqualsAndHashCode(of = "pid")
public class PDSBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
    private String pname;
    private String pwriter;

    // 연관관계 설정 : 단반향, 일대다
    @OneToMany(cascade = CascadeType.ALL) // cascade 영속성전이 설정 
    @JoinColumn(name = "pdsno")
    private List<PDSFile> files;
}
