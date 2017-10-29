package com.doubles.myboot01.domain;

public class Member {

    private String memberId;
    private String memberPw;
    private Address address;

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", memberPw='" + memberPw + '\'' +
                ", address=" + address +  // 무한 루프에 빠지게 된다.
                '}';
    }
}
