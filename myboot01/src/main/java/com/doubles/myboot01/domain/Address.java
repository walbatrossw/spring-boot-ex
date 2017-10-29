package com.doubles.myboot01.domain;

public class Address {

    private String zipcode;
    private String city;
    private Member member;

    @Override
    public String toString() {
        return "Address{" +
                "zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", member=" + member +  // 무한 루프에 빠지게 된다.
                '}';
    }
}
