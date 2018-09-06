package com.doubles.bootboard.tutorials.member.domain;

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
@Table(name = "tbl_profiles")
@EqualsAndHashCode(of = "profileId")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long profileId;     // 프로필이미지 번호
    private String fileName;    // 파일명
    private boolean current;    // 파일 사용유무

    @ManyToOne
    private Member member;      // 회원

    @CreationTimestamp
    private Timestamp regDate;  // 파일 등록일자

    @UpdateTimestamp
    private Timestamp updateDate;   // 파일 수정일자

}
