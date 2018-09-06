package com.doubles.bootboard.tutorials.article.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleId; // 게시글 번호
    private String title;   // 게시글 제목
    private String writer;  // 게시글 작성자
    private String content; // 게시글 내용

    @CreationTimestamp
    private Timestamp regDate;  // 게시글 등록일자

    @UpdateTimestamp
    private Timestamp updateDate;   // 게시글 수정일자

}
