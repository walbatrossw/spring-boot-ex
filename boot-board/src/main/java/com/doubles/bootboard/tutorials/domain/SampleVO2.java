package com.doubles.bootboard.tutorials.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"val3"})
public class SampleVO2 {

    private String val1;
    private String val2;
    private String val3;

}
