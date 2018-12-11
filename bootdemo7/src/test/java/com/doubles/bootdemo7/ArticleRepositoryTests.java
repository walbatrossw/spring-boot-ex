package com.doubles.bootdemo7;

import com.doubles.bootdemo7.article.domain.Article;
import com.doubles.bootdemo7.article.persistence.ArticleRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

}
