package com.doubles.bootdemo7;

import com.doubles.bootdemo7.article.domain.Article;
import com.doubles.bootdemo7.article.persistence.ArticleRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class ArticleRepositoryTests {

    @Autowired
    ArticleRepository articleRepository;

    // 게시글 더미 데이터 입력 테스트
    @Test
    public void insertArticles() {
        IntStream.range(0, 1000).forEach(i -> {
            Article article = new Article();
            article.setTitle("샘플 게시판 제목 " + i);
            article.setContent("샘플 게시판 내용 입니다...." + i + "반가워요!!!!!!!!!");
            article.setWriter("user0" + (i % 10));
            articleRepository.save(article);
        });
    }

    // 페이징 테스트
    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "articleNo");
        Page<Article> result = articleRepository.findAll(articleRepository.makePredicate(null, null), pageable);
        log.info("PAGE : " + result.getPageable());
        log.info("----------------------------------------------------");
        result.getContent().forEach(article -> log.info("" + article));
    }

    // 검색 조건 테스트
    @Test
    public void testSearching() {
        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "articleNo");
        Page<Article> result = articleRepository.findAll(articleRepository.makePredicate("t", "10"), pageable);
        log.info("PAGE : " + result.getPageable());
        log.info("----------------------------------------------------");
        result.getContent().forEach(article -> log.info("" + article));
    }

}
