package com.doubles.bootdemo9.reply.domain;

import com.doubles.bootdemo9.article.domain.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "tb_replies")
@EqualsAndHashCode(of = "replyNo")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNo;

    @Lob
    @Column(columnDefinition = "text")
    private String content;

    private String writer;

    @CreationTimestamp
    private Timestamp createDate;
    
    @UpdateTimestamp
    private Timestamp updateDate;

    @JsonIgnore // 상호 호출 방지
    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    private Article article;

}
