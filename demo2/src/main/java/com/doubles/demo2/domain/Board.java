package com.doubles.demo2.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Table(name = "tbl_boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;               // 게시글 번호
    private String title;           // 게시글 제목
    private String writer;          // 게시글 작성자
    private String content;         // 게시글 내용

    @CreatedDate
    private Timestamp regdate;      // 등록일자

    @UpdateTimestamp
    private Timestamp updatedate;   // 수정일자

}
