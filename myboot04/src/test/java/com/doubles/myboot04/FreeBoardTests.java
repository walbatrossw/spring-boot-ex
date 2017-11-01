package com.doubles.myboot04;

import com.doubles.myboot04.domain.FreeBoard;
import com.doubles.myboot04.domain.FreeBoardReply;
import com.doubles.myboot04.repository.FreeBoardReplyRepository;
import com.doubles.myboot04.repository.FreeBoardRepository;
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
    FreeBoardRepository boardRepo;

    @Autowired
    FreeBoardReplyRepository replyRepo;

    // 1. 게시물 등록 : 더미데이터 입력처리 테스트
    @Test
    public void insertDummy() {
        IntStream.range(1, 200).forEach(i->{
            FreeBoard freeBoard = new FreeBoard();
            freeBoard.setTitle("Free Board ..." + i);
            freeBoard.setContent("Free Content ..." + i);
            freeBoard.setWriter("user"+i%10);
            boardRepo.save(freeBoard);
        });
    }

    // 2. 댓글 등록 : 양방향 처리 테스트
    @Transactional
    @Test
    public void insertReply2Way() {

        Optional<FreeBoard> result = boardRepo.findById(199L);

        result.ifPresent(board -> {
            List<FreeBoardReply> replies = board.getReplies();
            FreeBoardReply reply = new FreeBoardReply();
            reply.setReply("Reply....");
            reply.setReplyer("replyer00");
            reply.setBoard(board);

            replies.add(reply);

            board.setReplies(replies);

            boardRepo.save(board);
        });
    }

    // 3. 댓글 등록 : 단방향 처리 테스트
    @Test
    public void insertReply1Way() {
        FreeBoard freeBoard = new FreeBoard();
        freeBoard.setBno(199L);
        FreeBoardReply reply = new FreeBoardReply();
        reply.setReply("Reply......");
        reply.setReplyer("replyer00");
        reply.setBoard(freeBoard);

        replyRepo.save(reply);
    }

    // 4. 게시물 목록, 페이징 처리 테스트 : 쿼리메서드 사용
    @Test
    public void testList1() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        boardRepo.findByBnoGreaterThan(0L, page).forEach(board -> {
            log.info(board.getBno() + ": " + board.getTitle());
        });
    }

    // 5. 게시물 목록, 페이징 처리 테스트2 : 지연로딩 VS, 즉시로딩
    @Transactional
    @Test
    public void testList2() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        boardRepo.findByBnoGreaterThan(0L, page).forEach(board -> {
            log.info(board.getBno() + ": " + board.getTitle() + ":" + board.getReplies().size());
        });
    }

    // 6. @Query외 Fetch Join을 이용한 처리 테스트
    @Test
    public void testList3() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
        boardRepo.getPage(page).forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }
}
