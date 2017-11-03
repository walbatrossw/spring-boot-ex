package com.doubles.myboot06;

import com.doubles.myboot06.domain.WebBoard;
import com.doubles.myboot06.persistence.WebBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WebBoardRepositoryTests {

    @Autowired
    WebBoardRepository repo;

    @Test
    public void insertBoardDummies() {
        IntStream.range(0,300).forEach(i->{
            WebBoard board = new WebBoard();
            board.setTitle("Sample Board Title" + i);
            board.setContent("Content Sample ..." + i + "of Board");
            board.setWriter("user0" + (1%10));
            repo.save(board);
        });
    }

    // 단순 페이징 처리 테스트
    @Test
    public void testList1() {
        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "bno");
        Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), pageable);

        log.info("PAGE : " + result.getPageable());
        log.info("--------------------------------");
        result.getContent().forEach(board -> log.info("" + board));
    }

    // 검색조건 테스트
    @Test
    public void testList2() {
        Pageable pageable = PageRequest.of(0, 20, Sort.Direction.DESC, "bno");
        Page<WebBoard> result = repo.findAll(repo.makePredicate("t", "10"), pageable);

        log.info("PAGE: " + result.getPageable());
        log.info("--------------------------------");
        result.getContent().forEach(board -> log.info("" + board));
    }
}
