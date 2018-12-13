package com.doubles.bootdemo7.reply.domain;

import com.doubles.bootdemo7.article.domain.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "tb_reply")
@EqualsAndHashCode(of = "replyNo")
@ToString
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNo;

    @Lob
    @Column(columnDefinition = "text")
    private String content;
    private String writer;

    @CreationTimestamp
    private Timestamp regDate;

    @CreationTimestamp
    private Timestamp updateDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

}
