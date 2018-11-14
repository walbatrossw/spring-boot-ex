package com.doubles.bootdemo4;

import com.doubles.bootdemo4.domain.PDSBoard;
import com.doubles.bootdemo4.domain.PDSFile;
import com.doubles.bootdemo4.persistence.PDSBoardRepository;
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

    /*@Modifying 애너테이션 사용*/

    // 첨부파일 수정1 : @Modifying 애너테이션 사용
    @Transactional  // 트랜잭션
    @Test
    public void testUpdateFileName1() {
        Long fno = 1L;
        String newName = "updatedFile1.doc";
        int count = pdsBoardRepository.updatePDSFile(fno, newName);
        log.info("UPDATE COUNT : " + count);
    }

    // 첨부파일 수정1 : 순수 객체 사용
    @Transactional
    @Test
    public void testUpdateFileName2() {
        String newName = "updatedFile2.doc";
        // 번호가 존재하는지 반드시 확인
        Optional<PDSBoard> result = pdsBoardRepository.findById(2L);
        result.ifPresent(pdsBoard -> {
            log.info("try to update...");
            PDSFile target = new PDSFile(); // 새로운 파일 객체 생성
            target.setFno(2L);              // 자료 게시글 번호
            target.setPdsfile(newName);     // 새로운 파일명
            int idx = pdsBoard.getFiles().indexOf(target);
            if (idx > -1) {
                // 첨부파일 삭제 -> 새로 추가
                List<PDSFile> list = pdsBoard.getFiles();
                list.remove(idx);
                list.add(target);
                pdsBoard.setFiles(list);
            }
            // 저장
            pdsBoardRepository.save(pdsBoard);
        });
    }

    // 첨부파일 삭제
    @Transactional
    @Test
    public void deletePDSFile() {
        Long fno = 2L;
        int count = pdsBoardRepository.deletePDSFile(fno);
        log.info("DELETE PDSFILE : " + count);
    }

    // 조인 처리
    @Test
    public void insertDummies() {

        List<PDSBoard> list = new ArrayList<>();

        IntStream.range(1, 100).forEach(i -> {

            PDSBoard  pdsBoard = new PDSBoard();
            pdsBoard.setPname("자료" + i);

            PDSFile file1 = new PDSFile();
            file1.setPdsfile("file1.doc");

            PDSFile file2 = new PDSFile();
            file2.setPdsfile("file2.doc");

            pdsBoard.setFiles(Arrays.asList(file1, file2));

            log.info("try to save pds");

            list.add(pdsBoard);
        });

        pdsBoardRepository.saveAll(list);
    }

    // 자료와 첨부파일의 수 역순 정렬
    @Test
    public void viewSummary() {
        pdsBoardRepository.getSummary().forEach(arr -> log.info(Arrays.toString(arr)));
    }
}
