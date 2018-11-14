package com.doubles.bootdemo4.persistence;

import com.doubles.bootdemo4.domain.FreeBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {

    /*게시물의 페이징처리, @Query 애너테이션*/

    // 쿼리 메서드를 이용하는 경우
    public List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable page);

    // @Query를 이용한 조인
    @Query("SELECT b.bno, b.title, COUNT(r) FROM FreeBoard b LEFT OUTER JOIN b.replies r WHERE b.bno > 0 GROUP BY b")
    public List<Object[]> getPage(Pageable page);

}
