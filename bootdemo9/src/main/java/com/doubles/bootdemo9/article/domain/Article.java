package com.doubles.bootdemo9.article.domain;

import com.doubles.bootdemo9.reply.domain.Reply;
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

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY) // 지연로딩
    private List<Reply> replies;

}
