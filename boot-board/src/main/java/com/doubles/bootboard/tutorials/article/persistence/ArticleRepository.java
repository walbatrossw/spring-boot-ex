package com.doubles.bootboard.tutorials.article.persistence;

import com.doubles.bootboard.tutorials.article.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.Collection;
import java.util.List;

// 게시글 Repository 인터페이스
public interface ArticleRepository extends CrudRepository<Article, Long>, QuerydslPredicateExecutor<Article> {

    // --- 쿼리 메서드 ---

    // 제목으로 게시글 조회
    public List<Article> findArticleByTitle(String title);

    // 작성자로 게시글 조회
    public Collection<Article> findByWriter(String writer);

    // 작성자 like 게시글 조회
    public Collection<Article> findByWriterContaining(String writer);

    // 제목, 내용 like 게시글 조회
    public Collection<Article> findByTitleContainingOrContentContaining(String title, String content);

    // 제목 like and 게시글 번호 조회
    public Collection<Article> findByTitleContainingAndArticleIdGreaterThan(String keyword, Long articleId);

    // 게시글 번호 조회, 역순 정렬
    public Collection<Article> findByArticleIdGreaterThanOrderByArticleIdDesc(Long articleId);

    // 게시글 번호 조회, 역순 정렬, 페이징
    public List<Article> findByArticleIdGreaterThanOrderByArticleIdDesc(Long articleId, Pageable paging);

    // 게시글 번호 조회, 페이징
    public Page<Article> findByArticleIdGreaterThan(Long id, Pageable paging);

    // --- @Query 애너테이션 ---

    // 제목에 대한 검색 처리
    @Query("SELECT a FROM Article a WHERE a.title LIKE %?1% AND a.id > 0 ORDER BY a.id DESC")
    public List<Article> findByTitle(String title);

    // 내용에 대한 검색 처리 @Param
    @Query("SELECT a FROM Article a WHERE a.content LIKE %:content% AND a.id > 0 ORDER BY a.id DESC")
    public List<Article> findByContent(@Param("content") String content);

    // 작성자에 대한 검색 처리 #{#entityName}
    @Query("SELECT a FROM #{#entityName} a WHERE a.writer LIKE %?1% AND a.id > 0 ORDER BY a.id DESC")
    public List<Article> findByWriter2(String writer);

    // 필요한 칼럼만 추출할 경우
    @Query("SELECT a.articleId, a.title, a.writer, a.regDate FROM Article a WHERE a.title LIKE %?1% AND a.articleId > 0 ORDER BY a.id DESC")
    public List<Object[]> findByTitle2(String title);

    // nativeQuery 사용
    @Query(value = "SELECT articleId, title, writer FROM tbl_article WHERE title like CONCAT('%', ?1, '%') AND articleId > 0 ORDER BY articleId DESC", nativeQuery = true)
    public List<Object[]> findByTitle3(String title);

    // @Query와 Paging 처리/정렬
    @Query("SELECT a FROM Article a WHERE a.articleId > 0 ORDER BY a.articleId DESC")
    public List<Article> findByPage(Pageable pageable);



}
