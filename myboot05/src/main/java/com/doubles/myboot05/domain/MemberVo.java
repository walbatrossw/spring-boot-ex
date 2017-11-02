package com.doubles.myboot05.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class MemberVo {

    private int mno;
    private String mid;
    private String mpw;
    private String mname;
    private Timestamp regdate;

}
