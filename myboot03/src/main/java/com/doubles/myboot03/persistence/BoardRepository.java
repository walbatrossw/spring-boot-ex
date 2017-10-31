package com.doubles.myboot03.persistence;

import com.doubles.myboot03.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long>, QueryDslPredicateExecutor<Board> {

    // 쿼리 메서드 작성
    // 1. findBy 게시글 제목으로 조회
    public List<Board> findBoardByTitle(String title);

    // 2. findBy 특정 칼럼 처리
    public Collection<Board> findByWriter(String writer);

    // 3. LIKE 구문
    // 1) 단순 like : Like
    // 2) 키워드 + '%' : StartingWith
    // 3) '%' + 키워드 : EndingWith
    // 3) '%' + 키워드, '%' + 키워드 + '%' : Containing
    public Collection<Board> findByWriterContaining(String writer);

    // 4. OR 조건
    public Collection<Board> findByTitleContainingOrContentContaining(String writer, String content);

    // 5. AND 조건, 부등호 처리 : GreaterThan(>), LessThan(<)
    // 게시물의 특정 문자를 포함하고, 게시물번호(bno)가 특정 숫자 이상인 데이터를 조회할 경우
    // title LIKE %?% AND BNO > ?
    public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

    // 6. 정렬 : ORDER BY
    // bno > ? ORDER BY bno DESC
    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    // 7. 기본적인 페이징, 정렬 처리 : 페이징 처리시에는 List타입을 이용!!!
    // bno > ? ORDER BY bno DESC limit ?, ?
    public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);

    // 8. 인터페이스에 페이징, 정렬 처리
    //public List<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    // 9. 인터페이스에 페이징, 정렬 처리 2 : Page<T> 타입
    public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    // @Query 기본 작성
    // 10. 제목에 대한 검색처리
    @Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findByTitle(String title);

    // 11. 내용에 대한 검색 처리 : @Param
    @Query("SELECT b FROM Board b WHERE b.content LIKE %:content% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Board> findByContent(@Param("content") String content);

    // 12. 작성자에 대한 검색처리 : #{#entityName}
//    @Query("SELECT b FROM #{#entityName} b WHERE b.writer LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
//    List<Board> findByWriter(String writer);

    // 13. 필요한 칼럼만 추출하는 경우 : ex) content를 제외한 나머지
    @Query("SELECT b.bno, b.title, b.writer, b.regdate, b.updatedate FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
    public List<Object[]> findByTitle2(String title);

    // 14. nativeQuery 사용
//    @Query(value = "SELECT bno, title, writer FROM tbl_boards WHERE title LIKE CONCAT('%', '?1', '%') AND bno > 0 ORDER BY bno desc", nativeQuery = true)
//    List<Object[]> findByTitle3(String title);

    // 15. @Query와 Paging 처리/정렬
    @Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno DESC ")
    public List<Board> findByPage(Pageable pageable);
}
