package com.doubles.myboot06.vo;

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

    private Pageable prevPage;       // 이전페이지 정보
    private Pageable nextPage;      // 다음페이지 정보

    private int currentPageNum;     // 화면에 보이는 1부터 시작하는 페이지 번호
    private int totalPageNum;

    private Pageable currentPage;   // 현재페이지 정보

    private List<Pageable> pageList;    // 페이지번호의 시작 ~ 끝까지의 Pageable 리스트

    public PageMaker(Page<T> result) {

        this.result = result;

        this.currentPage = result.getPageable();

        this.currentPageNum = currentPage.getPageNumber() + 1;

        this.totalPageNum = result.getTotalPages();

        this.pageList = new ArrayList<>();

        calcPages();
    }

    // 페이지 계산
    private void calcPages() {

        int tempEndNum = (int) (Math.ceil(this.currentPageNum / 10.0) * 10);

        int startNum = tempEndNum - 9;

        Pageable startPage = this.currentPage;

        // move to start Pageable
        for (int i = startNum; i < this.currentPageNum; i++) {
            startPage = startPage.previousOrFirst();
        }
        this.prevPage = startPage.getPageNumber() <= 0 ? null : startPage.previousOrFirst();

        // log.info("tempEndNum: " + tempEndNum)
        // log.info("total: " + totalPageNum)

        if (this.totalPageNum < tempEndNum) {
            tempEndNum = this.totalPageNum;
            this.nextPage = null;
        }

        for (int i = startNum; i <= tempEndNum; i++) {
            pageList.add(startPage);
            startPage = startPage.next();
        }
        this.nextPage = startPage.getPageNumber() + 1 < totalPageNum ? startPage: null;
    }
}
