package com.doubles.bootdemo7.article.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_article")
@EqualsAndHashCode(of = "articleNo")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleNo;     // 게시물 번호

    private String title;       // 게시물 제목
    private String writer;      // 게시물 작성자
    @Lob
    @Column(columnDefinition = "text")
    private String content;     // 게시물 내용

    @CreationTimestamp
    private Timestamp regDate;  // 게시물 작성일자

    @UpdateTimestamp
    private Timestamp updateDate;   // 게시물 수정일자

}
