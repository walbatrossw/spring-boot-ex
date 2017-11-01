package com.doubles.myboot04;

import com.doubles.myboot04.domain.PDSBoard;
import com.doubles.myboot04.domain.PDSFile;
import com.doubles.myboot04.repository.PDSBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class PDSBoardTests {

    @Autowired
    PDSBoardRepository pdsBoardRepo;

    // 1. 등록과 Cascading 처리 테스트
    @Test
    public void testInsertPDS() {
        PDSBoard pds = new PDSBoard();
        pds.setPname("DOCUMENT 1 - 2");

        PDSFile file1 = new PDSFile();
        file1.setPdsfile("file1.doc");

        PDSFile file2 = new PDSFile();
        file2.setPdsfile("file2.doc");

        pds.setFiles(Arrays.asList(file1, file2));

        log.info("try to save pds");

        pdsBoardRepo.save(pds);
    }

    // 2. 첨부파일 수정 테스트 1 : @Query, @Modifying
    @Transactional
    @Test
    public void testUpdateFileName1() {
        Long fno = 1L;
        String newName = "updatedFile.doc";
        int count = pdsBoardRepo.updatePDSFile(fno, newName);
        log.info("update count : " + count);
    }

    // 3. 첨부파일 수정 테스트 2 : 순수한 객체를 통한 파일 수정
    @Transactional
    @Test
    public void testUpdateFileName2() {
        String newName = "updatedFile2.doc";

        // 반드시 번호가 존재하는지 확인할 것
        Optional<PDSBoard> result = pdsBoardRepo.findById(2L);
        // 데이터가 존재한다면
        result.ifPresent(pds->{
            log.info("데이터가 존재하므로 update 시도");
            PDSFile target = new PDSFile();
            target.setFno(2L);
            target.setPdsfile(newName);

            int idx = pds.getFiles().indexOf(target);
            if (idx > -1) {
                List<PDSFile> list = pds.getFiles();
                list.remove(idx);
                list.add(target);
                pds.setFiles(list);
            }
            pdsBoardRepo.save(pds);
        });
    }

    // 4. 첨부파일 삭제 테스트
    @Transactional
    @Test
    public void testDeletePDSFile() {
        // 첨부파일 번호
        Long fno = 2L;
        int count = pdsBoardRepo.deletePDSFile(fno);
        log.info("DELETE PDSFILE : " + count);
    }

    // 5. 조인처리를 위한 더미데이터 입력
    @Test
    public void insertDummies() {
        List<PDSBoard> list = new ArrayList<>();

        IntStream.range(1, 100).forEach(i -> {
            PDSBoard pdsBoard = new PDSBoard();
            pdsBoard.setPname("자료 " + i);

            PDSFile file1 = new PDSFile();
            file1.setPdsfile("file1.doc");

            PDSFile file2 = new PDSFile();
            file2.setPdsfile("file2.doc");

            pdsBoard.setFiles(Arrays.asList(file1, file2));
            log.info("try to save pds");
            list.add(pdsBoard);
        });
        pdsBoardRepo.saveAll(list);
    }

    @Test
    public void testViewSummary() {
        pdsBoardRepo.getSummary().forEach(arr->log.info(Arrays.toString(arr)));
    }
}
