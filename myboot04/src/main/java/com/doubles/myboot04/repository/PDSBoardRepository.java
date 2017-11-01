package com.doubles.myboot04.repository;

import com.doubles.myboot04.domain.PDSBoard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long>{

    // 1. 첨부파일 수정
    @Modifying
    @Query("UPDATE FROM PDSFile f SET f.pdsfile = ?2 WHERE f.fno = ?1 ")
    public int updatePDSFile(Long fno, String newFileName);

    // 2. 첨부파일의 삭제
    @Modifying
    @Query("DELETE FROM PDSFile f WHERE f.fno = ?1")
    public int deletePDSFile(Long fno);

    // 3. 자료와 첨부파일의 수를 자료번호 역순으로 출력하기
    @Query("SELECT p, count(f) FROM PDSBoard p LEFT OUTER JOIN p.files f ON p.pid = f WHERE p.pid > 0 GROUP BY p ORDER BY p.pid DESC")
    public List<Object[]> getSummary();
}
