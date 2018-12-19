package com.doubles.bootdemo9.reply.controller;

import com.doubles.bootdemo9.article.domain.Article;
import com.doubles.bootdemo9.reply.domain.Reply;
import com.doubles.bootdemo9.reply.persistence.ReplyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Log
@RestController
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyController(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    // 댓글 추가
    @PostMapping("/{articleNo}")
    public ResponseEntity<List<Reply>> write(@PathVariable("articleNo") Long articleNo, @RequestBody Reply reply) {

        log.info("reply write() called ...");
        log.info("articleNo : " + articleNo);
        log.info("reply : " + reply);

        Article article = getArticle(articleNo);
        reply.setArticle(article);

        replyRepository.save(reply);

        return new ResponseEntity<>(getListByArticle(article), HttpStatus.CREATED);
    }

    // 댓글 수정
    @Transactional
    @PutMapping("/{articleNo}")
    public ResponseEntity<List<Reply>> modify(
            @PathVariable("articleNo") Long articleNo,
            @RequestBody Reply reply) {

        log.info("reply modify() called ... ");
        log.info("reply : " + reply);

        replyRepository.findById(reply.getReplyNo()).ifPresent(origin -> {
            origin.setContent(reply.getContent());
            replyRepository.save(origin);
        });

        Article article = getArticle(articleNo);

        return new ResponseEntity<>(getListByArticle(article), HttpStatus.CREATED);
    }

    // 댓글 삭제
    @Transactional
    @DeleteMapping("/{articleNo}/{replyNo}")
    public ResponseEntity<List<Reply>> remove(
            @PathVariable("articleNo") Long articleNo,
            @PathVariable("replyNo") Long replyNo) {

        log.info("reply remove() called ... ");
        log.info("reply : " + replyNo);

        replyRepository.deleteById(replyNo);

        Article article = getArticle(articleNo);

        return new ResponseEntity<>(getListByArticle(article), HttpStatus.OK);
    }

    // 댓글 목록
    @GetMapping("/{articleNo}")
    public ResponseEntity<List<Reply>> getReplies(@PathVariable("articleNo") Long articleNo) {

        log.info("reply getReplies() called ...");

        Article article = getArticle(articleNo);

        return new ResponseEntity<>(getListByArticle(article), HttpStatus.OK);
    }

    // 댓글 목록 반환
    private List<Reply> getListByArticle(Article article) throws RuntimeException {

        log.info("getListByArticle() called ... " + article);

        return replyRepository.getRepliesOfArticle(article);
    }

    // 게시글 객체 생성, 게시글 번호 세팅
    private Article getArticle(Long articleNo) {

        Article article = new Article();
        article.setArticleNo(articleNo);

        return article;
    }
}
