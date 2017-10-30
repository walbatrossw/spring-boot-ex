package com.doubles.myboot02.domain;


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
@Table(name = "tbl_boards")
public class Board {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.AUTO) // 식별키를 부여하는 방식, 자동
    private Long bno;

    private String title;

    private String writer;

    private String content;

    @CreationTimestamp  // 엔티티가 생성되는 시점
    private Timestamp regdate;      // LocalDateTime

    @UpdateTimestamp    // 엔티티가 수정되는 시점
    private Timestamp updatedate;   // LocalDateTime
}
