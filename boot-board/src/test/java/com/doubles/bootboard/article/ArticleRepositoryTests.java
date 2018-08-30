package com.doubles.bootboard.article;

import com.doubles.bootboard.article.domain.Article;
import com.doubles.bootboard.article.persistence.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositoryTests {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void inspect() {

        Class<?> clz = articleRepository.getClass();

        System.out.println(clz.getName());

        Class<?>[] interfaces = clz.getInterfaces();

        Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));

        Class<?> superClass = clz.getSuperclass();
        System.out.println(superClass.getName());

    }

    @Test
    public void testInsert() {

        Article article = new Article();
        article.setTitle("게시글 제목");
        article.setContent("게시글 내용");
        article.setWriter("user00");

        articleRepository.save(article);
    }

    @Test
    public void testRead() {
        Article article = articleRepository.findById(1L).get();
        System.out.println(article);
    }

    @Test
    public void testUpdate() {

        System.out.println("Read First ....");
        Article article = articleRepository.findById(1L).get();

        System.out.println("Update Title ....");
        article.setTitle("수정된 제목");

        System.out.println("Call save() ....");
        articleRepository.save(article);

    }

    @Test
    public void testDelete() {

        System.out.println("Delete Entity ....");
        articleRepository.deleteById(1L);

    }
}
