package com.doubles.bootdemo7.article.controller;

import com.doubles.bootdemo7.article.domain.Article;
import com.doubles.bootdemo7.article.persistence.ArticleRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log
@Controller
@RequestMapping("/articles")
public class ArticleController {


    // 게시물 목록
    @GetMapping("")
    public String getArticles() {

        log.info("");
        log.info("");

        return "/articles/list";
    }

    // 게시물 작성 페이지
    @GetMapping("/write")
    public void write() {
        log.info("");
    }

    // 게시물 작성 처리
    @PostMapping("/write")
    public String write(@ModelAttribute("articles") Article article, RedirectAttributes redirectAttributes) {
        return "redirect:/articles";
    }

    // 게시물 조회
    @GetMapping("/read")
    public void read() {

    }

    // 게시물 수정 페이지
    @GetMapping("/modify")
    public void modify(Long articleNo) {

    }

    // 게시물 수청 처리
    @PostMapping("/modify")
    public void modify(Article article) {

    }

    // 게시물 삭제 처리
    @PostMapping("/delete")
    public String remove(Long articleNo) {
        return "redirect:/articles";
    }

}
