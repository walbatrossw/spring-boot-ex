package com.doubles.bootdemo9;

import com.doubles.bootdemo9.article.domain.Article;
import com.doubles.bootdemo9.article.persistence.ArticleRepository;
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
public class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    // 게시글 더미 데이터 입력
    @Test
    public void insertArticles() {

        IntStream.range(0, 1000).forEach(i -> {
            Article article = new Article();
            article.setTitle("샘플 게시글 제목 입니다. " + i);
            article.setContent("샘플 게시글 내용입니다........" + i);
            article.setWriter("user0" + (i % 10));
            articleRepository.save(article);
        });

    }

    // 게시글 목록 테스트 : 검색 조건 X
    @Test
    public void getArticles() {

        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "articleNo");
        Page<Article> result = articleRepository.findAll(articleRepository.makePredicate(null, null), pageable);

        log.info("PAGE : " + result.getPageable());
        log.info("--------------------------------------------------------------------------------------");
        result.getContent().forEach(article -> log.info("" + article));

    }

    // 게시글 목록 테스트 : 검색 조건 O
    @Test
    public void getArticlesBySearchType() {

        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "articleNo");
        Page<Article> result = articleRepository.findAll(articleRepository.makePredicate("t", "10"), pageable);
        log.info("PAGE : " + result.getPageable());
        log.info("--------------------------------------------------------------------------------------");
        result.getContent().forEach(article -> log.info("" + article));

    }

}
