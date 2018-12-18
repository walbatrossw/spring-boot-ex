package com.doubles.bootdemo9.article.domain;

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
@Entity
@Table(name = "tb_articles")
@ToString
@EqualsAndHashCode(of = "articleNo")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleNo;

    private String title;

    @Lob
    @Column(columnDefinition = "text")
    private String content;

    private String writer;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp UpdateDate;

}
