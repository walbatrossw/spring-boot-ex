package com.doubles.bootdemo6.board.vo;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = "pageList")
@Log
public class PageMaker<T> {

    private Page<T> result;

    private Pageable prevPage;
    private Pageable nextPage;

    private int currentPageNum;
    private int totalPageNum;

    private Pageable currentPage;

    private List<Pageable> pageList;

    // 생성자
    public PageMaker(Page<T> result) {
        this.result = result;

        this.currentPage = result.getPageable();

        this.currentPageNum = currentPage.getPageNumber() + 1;
        this.totalPageNum = result.getTotalPages();

        this.pageList = new ArrayList<>();
    }

    private void calcPages() {
        int tempEndNum = (int) (Math.ceil(this.currentPageNum / 10.0) * 10);
        int startNum = tempEndNum - 9;

        Pageable startPage = this.currentPage;

        // 첫페이지로 이동
        for (int i = startNum; i < this.currentPageNum; i++) {
            startPage = startPage.previousOrFirst();
        }
        this.prevPage = startPage.getPageNumber() <= 0 ? null : startPage.previousOrFirst();

        log.info("tempEndNum : " + tempEndNum);
    }

}
