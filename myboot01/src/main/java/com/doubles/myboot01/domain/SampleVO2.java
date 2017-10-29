package com.doubles.myboot01.domain;

import lombok.Data;
import lombok.ToString;

@Data   // getter, setter, toString 메서드 등을 묶음으로 사용할때 사용
@ToString(exclude = {"value3"}) // 특정 속성을 출력하지 않도록할 때 사용
public class SampleVO2 {

    private String value1;
    private String value2;
    private String value3;
}
