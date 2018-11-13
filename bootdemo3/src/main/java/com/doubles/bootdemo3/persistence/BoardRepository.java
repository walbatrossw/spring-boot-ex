package com.doubles.bootdemo3.persistence;

import com.doubles.bootdemo3.domain.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long>, QueryDslPredicateExecutor<Board> {

    /*쿼리메서드*/

    // findBoardBy
    public List<Board> findBoardByTitle(String title);

    // findBy
    //public Collection<Board> findByWriter(String writer);

    // like 구문
    public Collection<Board> findByWriterContaining(String writer);

    // and or 조건
    public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

    // 부등호 처리
    public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

    // Order by
    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    // bno > ? order by bno desc limit ?, ?
    public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);

    // bno > ?
    //public List<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    // Page<T> 타입
    public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    /*@Query 애너테이션*/

    // 제목으로 검색 처리
    @Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findByTitle(String title);

    // 내용에 대한 검색 처리 : @Param 애너테이션
    @Query("SELECT b FROM Board b WHERE b.content LIKE %:content% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findByContent(@Param("content") String content);

    // 작성자에 대한 검색 처리 : #{#entityName}
    @Query("SELECT b FROM #{#entityName} b WHERE b.writer LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findByWriter(String writer);

    // 필요한 칼럼만 추출하는 경우
    @Query("SELECT b.bno, b.title, b.writer, b.regdate FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Object[]> findByTitle2(String title);

    // nativeQuery 사용
    @Query(value = "SELECT bno, title, writer FROM tbl_boards WHERE title like CONCAT('%', ?1, '%') AND bno > 0 ORDER BY bno desc", nativeQuery = true)
    public List<Object[]> findByTitle3(String title);

    // @Query와 Paging 처리, 정렬
    @Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findByPage(Pageable paging);

}
