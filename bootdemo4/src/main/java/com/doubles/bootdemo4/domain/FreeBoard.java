package com.doubles.bootdemo4.domain;

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
@ToString(exclude = "replies")
@Entity
@Table(name = "tbl_freeboards", indexes = {@Index(unique = false, columnList = "board_bno")})   // 인덱스 추가
@EqualsAndHashCode(of = "bno")
public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String writer;
    private String content;

    @CreationTimestamp
    private Timestamp regDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    // 일대다 연관관계
    @OneToMany(mappedBy = "freeBoard",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FreeBoardReply> replies;

}
