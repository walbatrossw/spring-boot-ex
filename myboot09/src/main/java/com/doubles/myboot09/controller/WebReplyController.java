package com.doubles.myboot09.controller;

import com.doubles.myboot09.domain.WebBoard;
import com.doubles.myboot09.domain.WebReply;
import com.doubles.myboot09.persistence.WebReplyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/replies/*")
@Log
public class WebReplyController {

    @Autowired
    private WebReplyRepository replyRepo;

    // 특정 게시물의 댓글 등록처리, 목록처리
    @Transactional
    @PostMapping("/{bno}")
    public ResponseEntity<List<WebReply>> addReply(@PathVariable("bno")Long bno, @RequestBody WebReply reply) {
        log.info("Add Reply...");
        log.info("BNO : " + bno);
        log.info("REPLY : " + reply);
        WebBoard board = new WebBoard();
        board.setBno(bno);
        reply.setBoard(board);
        replyRepo.save(reply);
        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
    }

    // 댓글 삭제
    @Transactional
    @DeleteMapping("/{bno}/{rno}")
    public ResponseEntity<List<WebReply>> remove(@PathVariable("bno")Long bno, @PathVariable("rno")Long rno) {
        log.info("delete reply" + rno);
        replyRepo.deleteById(rno);
        WebBoard board = new WebBoard();
        board.setBno(bno);
        return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
    }

    // 댓글 수정
    @Transactional
    @PutMapping("/{bno}")
    public ResponseEntity<List<WebReply>> modify(@PathVariable("bno")Long bno, @RequestBody WebReply reply) {
        log.info("modify reply" + reply);
        replyRepo.findById(reply.getRno()).ifPresent(origin -> {
            origin.setReplyText(reply.getReplyText());
            replyRepo.save(origin);
        });
        WebBoard board = new WebBoard();
        board.setBno(bno);
        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
    }

    // 댓글 목록
    @GetMapping("/{bno}")
    public ResponseEntity<List<WebReply>> getReplies(@PathVariable("bno")Long bno) {
        log.info("get All Replies");
        WebBoard board = new WebBoard();
        board.setBno(bno);
        return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
    }

    private List<WebReply> getListByBoard(WebBoard board) throws RuntimeException {
        log.info("getListByBoard..." + board);
        return replyRepo.getRepliesOfBoard(board);
    }
}
