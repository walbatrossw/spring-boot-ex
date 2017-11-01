package com.doubles.myboot04.repository;

import com.doubles.myboot04.domain.FreeBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long>{

    // 게시물 목록, 페이징 처리1 : 쿼리 메서드 사용
    public List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable page);

    // 게시물 목록, 페이징 처리2 : @Query, Fetch Join 사용
    @Query("SELECT b.bno, b.title, count(r) FROM FreeBoard b LEFT OUTER JOIN b.replies r WHERE b.bno > 0 GROUP BY b")
    public List<Object[]> getPage(Pageable page);
}
