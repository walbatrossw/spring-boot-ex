package com.doubles.bootdemo9.article.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Log
@ToString(exclude = "pageList")
public class PageMaker<T> {

    private Page<T> result;
    private Pageable prevPage;  // 이전 페이지
    private Pageable nextPage;  // 다음 페이지
    private Pageable currentPage; // 현재 페이지

    private int currentPageNum; // 현재 페이지 번호
    private int totalPageNum; // 전체 페이지 수

    private List<Pageable> pageList; // 페이지 리스트

    // 생성자
    public PageMaker(Page<T> result) {
        this.result = result;
        this.currentPage = result.getPageable();
        this.currentPageNum = currentPage.getPageNumber() + 1;
        this.totalPageNum = result.getTotalPages();
        this.pageList = new ArrayList<>();
        calcPages();
    }

    // 페이징 번호 계산
    private void calcPages() {

        int tempEndNum = (int) (Math.ceil(this.currentPageNum / 10.0) * 10); // 페이지 끝번호 계산
        int startNum = tempEndNum - 9; // 페이지 시작 번호 계산
        Pageable stratPage = this.currentPage;  // 시작 페이지 계산을 위해 현재 페이지 할당

        // 시작 페이지 부터 현재 페이지까지 반복 수행
        for (int i = startNum; i < this.currentPageNum; i++) {
            stratPage = stratPage.previousOrFirst();
        }
        // 페이지 번호가 0보다 작거나 같으면 이전 페이지는 null
        this.prevPage = stratPage.getPageNumber() <= 0 ? null : stratPage.previousOrFirst();

        log.info("startNum : " + startNum);
        log.info("tempEndNum : " + tempEndNum);
        log.info("total : " + totalPageNum);

        // 끝 페이지 번호 보정 처리 : 전체 페이지 수가 끝 페이지 끝번호보다 작을 경우
        if (this.totalPageNum < tempEndNum) {
            tempEndNum = this.totalPageNum;
            this.nextPage = null;
        }

        log.info("modified tempEndNum : " + tempEndNum);

        // 페이지 리스트에 페이지 정보 저장
        for (int i = startNum; i <= tempEndNum; i++) {
            pageList.add(stratPage); // 페이지 리스트에 저장
            stratPage = stratPage.next();
        }

        // 다음 페이지가 없을 경우 null
        this.nextPage = stratPage.getPageNumber() + 1 < totalPageNum ? stratPage : null;
    }
}
