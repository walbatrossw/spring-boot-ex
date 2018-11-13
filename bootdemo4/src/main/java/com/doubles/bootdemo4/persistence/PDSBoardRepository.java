package com.doubles.bootdemo4.persistence;

import com.doubles.bootdemo4.domain.PDSBoard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long> {

    @Modifying
    @Query("UPDATE FROM PDSFile set pdsfile = ?2 where fno = ?1")
    public int updatePDSFile(Long fno, String newFileName);




}
