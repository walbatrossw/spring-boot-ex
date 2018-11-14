package com.doubles.bootdemo4;

import com.doubles.bootdemo4.domain.FreeBoard;
import com.doubles.bootdemo4.domain.FreeBoardReply;
import com.doubles.bootdemo4.persistence.FreeBoardReplyRepository;
import com.doubles.bootdemo4.persistence.FreeBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTests {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @Autowired
    FreeBoardReplyRepository freeBoardReplyRepository;

    // 게시글 삽입
    @Test
    public void insertDummy() {
        IntStream.range(1, 200).forEach(i -> {
            FreeBoard freeBoard = new FreeBoard();
            freeBoard.setTitle("FreeBoard" + i);
            freeBoard.setContent("FreeBoard Content..." + i);
            freeBoard.setWriter("user" + i % 10);
            freeBoardRepository.save(freeBoard);
        });
    }

    // 댓글 삽입 : 양방향
    @Transactional
    @Test
    public void insertReply2Way() {
        Optional<FreeBoard> result = freeBoardRepository.findById(199L);

        result.ifPresent(freeBoard -> {

            List<FreeBoardReply> replies = freeBoard.getReplies();
            FreeBoardReply reply = new FreeBoardReply();

            reply.setReply("REPLY .....");
            reply.setReplyer("replyer00");
            reply.setFreeBoard(freeBoard);

            replies.add(reply);

            freeBoard.setReplies(replies);
            freeBoardRepository.save(freeBoard);

        });
    }

    // 댓글 삽입 : 단방향
    @Test
    public void insertReply1Way() {
        FreeBoard freeBoard = new FreeBoard();
        freeBoard.setBno(199L);
        FreeBoardReply freeBoardReply = new FreeBoardReply();
        freeBoardReply.setReply("REPLY....");
        freeBoardReply.setReplyer("replyer00");
        freeBoardReply.setFreeBoard(freeBoard);

        freeBoardReplyRepository.save(freeBoardReply);
    }


    /*게시물 페이징 처리, @Query*/

    // 쿼리 메서드를 사용하는 경우
    @Test
    public void testList1() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        freeBoardRepository.findByBnoGreaterThan(0L, page).forEach(freeBoard -> {
            log.info(freeBoard.getBno() + " : " + freeBoard.getTitle());
        });
    }

    // 지연로딩(lazy loading) : 성능에 좋지 않음
    @Transactional
    @Test
    public void testList2() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        freeBoardRepository.findByBnoGreaterThan(0L, page).forEach(freeBoard -> {
            log.info(freeBoard.getBno() + " : " + freeBoard.getTitle() + " : " + freeBoard.getReplies().size());
        });
    }

    // @Query를 이용한 조인
    @Test
    public void testList3() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        freeBoardRepository.getPage(page).forEach(arr -> log.info(Arrays.toString(arr)));
    }
}
