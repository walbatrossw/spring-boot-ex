package com.doubles.bootdemo7.article.vo;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = "pageList")
public class PageMaker<T> {

    private Page<T> result;

    private Pageable prevPage;  // 이전
    private Pageable nextPage;  // 다음

    private int currentPageNum; // 페이지 번호
    private int totalPageNum;   // 전체 페이지 번호

    private Pageable currentPage;   // 현재 페이지정보

    private List<Pageable> pageList;    // 1부터 끝페이지까지 Pageable들을 저장한 리스트

    public PageMaker(Page<T> result) {
        this.result = result;
        this.currentPage = result.getPageable();
        this.currentPageNum = currentPage.getPageNumber() + 1;
        this.totalPageNum = result.getTotalPages();
        this.pageList = new ArrayList<>();

        calcPage();
    }

    private void calcPage() {

        int tempEndNum = (int) (Math.ceil(this.currentPageNum / 10.0) * 10);
        int startNum = tempEndNum - 9;
        Pageable startPage = this.currentPage;

        for (int i = startNum; i < this.currentPageNum; i++) {
            startPage = startPage.previousOrFirst();
        }

        this.prevPage = startPage.getPageNumber() <= 0 ? null : startPage.previousOrFirst();

        if (this.totalPageNum < tempEndNum) {
            tempEndNum = this.totalPageNum;
            this.nextPage = null;
        }

        for (int i = startNum; i <= tempEndNum; i++) {
            pageList.add(startPage);
            startPage = startPage.next();
        }

        this.nextPage = startPage.getPageNumber() + 1 < totalPageNum ? startPage : null;

    }
}
