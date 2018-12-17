package com.doubles.bootdemo9.article.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
@RequestMapping("/article")
public class ArticleController {

    // 게시물 목록
    @GetMapping("/list")
    public void list() {
        log.info("list() called ...");
    }

    // 게시물 작성 페이지
    @GetMapping("/write")
    public void writeGET() {
        log.info("write() get called ...");
    }

    // 게시물 작성 처리
    @PostMapping("/write")
    public String writePOST() {
        log.info("write() post called ...");
        return "redirect:/article/list";
    }

    // 게시물 조회 페이지
    @GetMapping("/read")
    public void read() {
        log.info("read() get called ...");
    }

    // 게시물 수정 페이지
    @GetMapping("/modify")
    public void modifyGET() {
        log.info("modify() get called ...");
    }

    // 게시물 수정 처리
    @PostMapping("/modify")
    public String modifyPOST() {
        log.info("modify() post called ...");
        return "redirect:/article/list";
    }
    // 게시물 삭제 처리
    @PostMapping("/remove")
    public String removePOST() {

        log.info("remove() post called ...");
        return "redirect:/article/list";
    }
}
