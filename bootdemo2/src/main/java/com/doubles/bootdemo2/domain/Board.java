package com.doubles.bootdemo2.domain;

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
@Entity // 엔티티 클래스 명시
@Table(name = "tbl_boards") // 테이블 이름
public class Board {

    @Id // 식별키
    @GeneratedValue(strategy = GenerationType.AUTO) // 식별키 생성전략
    private Long bno;               // 게시글 번호
    private String title;           // 게시글 제목
    private String writer;          // 게시글 작성자
    private String content;         // 게시글 내용

    @CreationTimestamp // hibernate
    private Timestamp regdate;      // 등록일자

    @UpdateTimestamp // hibernate
    private Timestamp updatedate;   // 수정일자

}
