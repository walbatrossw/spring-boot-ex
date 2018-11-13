package com.doubles.bootdemo4;

import com.doubles.bootdemo4.domain.PDSBoard;
import com.doubles.bootdemo4.domain.PDSFile;
import com.doubles.bootdemo4.persistence.PDSBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class PDSBoardsTest {

    @Autowired
    PDSBoardRepository pdsBoardRepository;

    @Test
    public void testInsertPDS() {
        PDSBoard pds = new PDSBoard();
        pds.setPname("DOCUMENT 1 - 2");

        PDSFile file1 = new PDSFile();
        file1.setPdsfile("file1.doc");

        PDSFile file2 = new PDSFile();
        file2.setPdsfile("file2.doc");

        pds.setFiles(Arrays.asList(file1, file2));

        log.info("try to save pds...");

        pdsBoardRepository.save(pds);
    }

    // @Modifying 애너테이션 사용
    @Transactional
    @Test
    public void testUpdateFileName1() {
        Long fno = 1L;
        String newName = "updatedFile1.doc";
        int count = pdsBoardRepository.updatePDSFile(fno, newName);
        log.info("UPDATE COUNT : " + count);
    }

}
