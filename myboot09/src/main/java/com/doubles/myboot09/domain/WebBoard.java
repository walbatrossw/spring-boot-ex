package com.doubles.myboot09.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Log
@Table(name = "tbl_webboards")
@EqualsAndHashCode(of = "bno")
@ToString(exclude = "replies")
public class WebBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;

    private String writer;

    private String content;

    @CreationTimestamp
    private Timestamp regdate;

    @UpdateTimestamp
    private Timestamp updatedate;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<WebReply> replies;
}
