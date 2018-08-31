package com.doubles.bootboard.article;

import com.doubles.bootboard.article.domain.Article;
import com.doubles.bootboard.article.domain.QArticle;
import com.doubles.bootboard.article.persistence.ArticleRepository;
import com.querydsl.core.BooleanBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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

    // 더미 데이터 입력 테스트
    @Test
    public void testInsert() {
        Article article = new Article();
        article.setTitle("게시글 제목");
        article.setContent("게시글 내용");
        article.setWriter("user00");

        articleRepository.save(article);
    }

    // 게시글 조회 테스트
    @Test
    public void testRead() {
        Article article = articleRepository.findById(1L).get();
        System.out.println(article);
    }

    // 게시글 수정 테스트
    @Test
    public void testUpdate() {
        System.out.println("Read First ....");
        Article article = articleRepository.findById(1L).get();

        System.out.println("Update Title ....");
        article.setTitle("수정된 제목");

        System.out.println("Call save() ....");
        articleRepository.save(article);
    }

    // 게시글 삭제 테스트
    @Test
    public void testDelete() {
        System.out.println("Delete Entity ....");
        articleRepository.deleteById(1L);
    }

    @Test
    public void testInsertArticles() {
        for (int i = 1; i <= 200; i++) {
            Article article = new Article();
            article.setTitle(i + "번째 제목");
            article.setContent(i + "번째 내용... " );
            article.setWriter("user0" + (i % 10));
            articleRepository.save(article);
        }
    }

    @Test
    public void testByTitle() {
        articleRepository.findArticleByTitle("1번째 제목")
                .forEach(article -> System.out.println(article));
    }

    @Test
    public void testByWriter() {
        articleRepository.findByWriter("user00")
                .forEach(article -> System.out.println(article));
    }

    @Test
    public void testByWriterContaining() {
        Collection<Article> results =  articleRepository.findByWriterContaining("05");
        results.forEach(article -> System.out.println(article));
    }

    @Test
    public void testByTitleOrContent() {
        Collection<Article> results = articleRepository.findByTitleContainingOrContentContaining("01", "1");
        results.forEach(article -> System.out.println(article));
    }

    @Test
    public void testByTitleAndId() {
        Collection<Article> results = articleRepository.findByTitleContainingAndIdGreaterThan("5", 50L);
        results.forEach(article -> System.out.println(article));
    }

    @Test
    public void testIdOrderBy() {
        Collection<Article> results = articleRepository.findByIdGreaterThanOrderByIdDesc(90L);
        results.forEach(article -> System.out.println(article));
    }

    @Test
    public void testIdOrderByPaging() {
        Pageable paging = PageRequest.of(0, 10);
        Collection<Article> results = articleRepository.findByIdGreaterThanOrderByIdDesc(0L, paging);
        results.forEach(article -> System.out.println(article));
    }

    @Test
    public void testIdPagingSort() {
        Pageable paging = new PageRequest(0, 10, Sort.Direction.ASC, "id");
        Page<Article> results = articleRepository.findByIdGreaterThan(0L, paging);

        System.out.println("PAGE SIZE : " + results.getSize());
        System.out.println("TOTAL PAGES : " + results.getTotalPages());
        System.out.println("TOTAL COUNT : " + results.getTotalElements());
        System.out.println("NEXT : " + results.nextPageable());

        List<Article> list = results.getContent();
        list.forEach(article -> System.out.println(article));
    }

    @Test
    public void testByTitle2() {
        articleRepository.findByTitle("17").forEach(article -> System.out.println(article));
    }

    @Test
    public void testByContent() {
        articleRepository.findByContent("17").forEach(article -> System.out.println(article));
    }

    @Test
    public void testByWriter2() {
        articleRepository.findByWriter2("user00").forEach(article -> System.out.println(article));
    }

    @Test
    public void testByTitle17() {
        articleRepository.findByTitle2("17").forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    @Test
    public void testByTitle18() {
        articleRepository.findByTitle3("18").forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    @Test
    public void testByPaging() {
        Pageable pageable = PageRequest.of(0, 10);
        articleRepository.findByPage(pageable).forEach(article -> System.out.println(article));
    }

    @Test
    public void testPredicate() {
        String type = "t";
        String keyword = "17";

        BooleanBuilder builder = new BooleanBuilder();
        QArticle article = QArticle.article;

        if (type.equals("t")) {
            builder.and(article.title.like("%" + keyword + "%"));
        }

        builder.and(article.id.gt(0L));

        Pageable pageable = PageRequest.of(0, 10);
        Page<Article> result = articleRepository.findAll(builder, pageable);

        System.out.println("PAGE SIZE : " + result.getSize());
        System.out.println("TOTAL PAGES : " + result.getTotalPages());
        System.out.println("TOTAL COUNT : " + result.getTotalElements());
        System.out.println("NEXT : " + result.nextPageable());

        List<Article> list = result.getContent();

        list.forEach(a -> System.out.println(a));

    }
}
