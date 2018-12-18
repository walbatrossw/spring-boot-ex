package com.doubles.bootdemo9.article.vo;

import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@ToString
public class PageVO {

    private static final int DEFAULT_SIZE = 15;
    private static final int DEFAULT_MAX_SIZE = 50;

    private int page;
    private int size;

    private String keyword;
    private String type;

    public PageVO() {
        this.page = 1;
        this.size = DEFAULT_SIZE;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page < 0 ? 1 : page; // 페이지가 0보다 작을 경우 1로 지정
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size < DEFAULT_SIZE || size > DEFAULT_MAX_SIZE ? DEFAULT_SIZE : size; // 사이즈 범위보다 작거나 클 경우 기본사이즈로 지정
    }

    // Pageable 객체 생성 메서드
    public Pageable makePageable(int direction, String ... prop) {

        Sort.Direction dir = direction == 0 ? Sort.Direction.DESC : Sort.Direction.ASC; // 0이면 내림차순, 아니면 오름차순
        return PageRequest.of(this.page - 1, this.size, dir, prop); // 브라우저에 전달되는 page 값을 1부터 시작하도록 처리

    }



}
