package com.doubles.bootdemo7.reply.controller;

import com.doubles.bootdemo7.article.domain.Article;
import com.doubles.bootdemo7.reply.domain.Reply;
import com.doubles.bootdemo7.reply.persistence.ReplyRepository;
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

    // 댓글 입력
    @Transactional
    @PostMapping("/{articleNo}")
    public ResponseEntity<List<Reply>> addReply(@PathVariable("articleNo") Long articleNo, @RequestBody Reply reply) {

        log.info("addReply() called...");
        log.info("articleNo : " + articleNo);
        log.info("Reply : " + reply);

        Article article = new Article();
        article.setArticleNo(articleNo);
        reply.setArticle(article);
        replyRepository.save(reply);

        return new ResponseEntity<>(getRepliesByArticle(article), HttpStatus.CREATED);
    }

    // 댓글 삭제
    @Transactional
    @DeleteMapping("/{articleNo}/{replyNo}")
    public ResponseEntity<List<Reply>> remove(@PathVariable("articleNo") Long articleNo,
                                              @PathVariable("replyNo") Long replyNo) {

        log.info("remove reply : " + replyNo);
        replyRepository.deleteById(replyNo);

        Article article = new Article();
        article.setArticleNo(articleNo);

        return new ResponseEntity<>(getRepliesByArticle(article), HttpStatus.OK);
    }

    // 댓글 수정
    @Transactional
    @PutMapping("/{articleNo}")
    public ResponseEntity<List<Reply>> modify(@PathVariable("articleNo") Long articleNo, @RequestBody Reply reply) {
        log.info("modify reply : " + reply);

        replyRepository.findById(reply.getReplyNo()).ifPresent(origin -> {
            origin.setContent(reply.getContent());
            replyRepository.save(origin);
        });

        Article article = new Article();
        article.setArticleNo(articleNo);

        return new ResponseEntity<>(getRepliesByArticle(article), HttpStatus.CREATED);
    }


    // 댓글 목록
    @GetMapping("/{articleNo}")
    public ResponseEntity<List<Reply>> getReplies(@PathVariable("articleNo") Long articleNo) {
        log.info("replies() called");
        Article article = new Article();
        article.setArticleNo(articleNo);
        return new ResponseEntity<>(getRepliesByArticle(article), HttpStatus.OK);
    }

    // 댓글 목록
    private List<Reply> getRepliesByArticle(Article article) throws RuntimeException {
        log.info("getRepliesByArticle() called..." + article);
        return replyRepository.getReplyiesOfArticle(article);
    }
}
