package com.doubles.bootboard.tutorials.pds.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_psd")
@EqualsAndHashCode(of = "pdsId")
public class PDSBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pdsId;
    private String pdsName;
    private String pdsWriter;

    @OneToMany
    @JoinColumn(name = "pds")
    private List<PDSFile> files;

}
