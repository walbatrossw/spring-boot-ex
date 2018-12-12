package com.doubles.bootdemo7.article.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageVO {

    private static final int DEFAULT_SIZE = 10;     // 페이지 기본 사이즈
    private static final int DEFAULT_MAX_SIZE = 50; // 페이지 최대 사이즈

    private int page;
    private int size;

    public PageVO() {
        this.page = 1;
        this.size = DEFAULT_SIZE;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page < 0 ? 1 : page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size < DEFAULT_SIZE || size > DEFAULT_MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public Pageable makePageable(int direction, String ... prop) {
        Sort.Direction dir = direction == 0 ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(this.page - 1, this.size, dir, prop);
    }
}
