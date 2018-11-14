package com.doubles.bootdemo4.persistence;

import com.doubles.bootdemo4.domain.PDSBoard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long> {

    /*@Modifying 애너테이션 사용*/

    // 첨부파일 수정
    @Modifying
    @Query("UPDATE FROM PDSFile SET pdsfile = ?2 where fno = ?1")
    public int updatePDSFile(Long fno, String newFileName);


    // 첨부파일 삭제
    @Modifying
    @Query("DELETE FROM PDSFile f WHERE f.fno = ?1")
    public int deletePDSFile(Long fno);

    // 자료와 첨부파일의 수 역순정렬
    @Query("SELECT p, COUNT(f) FROM PDSBoard p LEFT OUTER JOIN p.files f ON p.pid = f WHERE p.pid > 0 GROUP BY  p ORDER BY p.pid DESC")
    public List<Object[]> getSummary();


}
