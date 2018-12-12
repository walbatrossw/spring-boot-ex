package com.doubles.bootdemo7.article.controller;

import com.doubles.bootdemo7.article.domain.Article;
import com.doubles.bootdemo7.article.persistence.ArticleRepository;
import com.doubles.bootdemo7.article.vo.PageMaker;
import com.doubles.bootdemo7.article.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    // 게시물 목록
    @GetMapping("/list")
    public void getArticles(PageVO pageVO, Model model) {

        Pageable page = pageVO.makePageable(0, "articleNo");
        Page<Article> result = articleRepository.findAll(articleRepository.makePredicate(null, null), page);
        log.info("" + page);
        log.info("" + result);
        log.info("Total Page Number : " + result.getTotalPages());

        model.addAttribute("result", new PageMaker<>(result));

    }

    // 게시물 작성 페이지
    @GetMapping("/write")
    public void write() {
        log.info("write get");
    }

    // 게시물 작성 처리
    @PostMapping("/write")
    public String write(@ModelAttribute("articles") Article article, RedirectAttributes redirectAttributes) {
        log.info("write post");
        return "redirect:/articles";
    }

    // 게시물 조회
    @GetMapping("/read")
    public void read() {
        log.info("read");
    }

    // 게시물 수정 페이지
    @GetMapping("/modify")
    public void modify(Long articleNo) {
        log.info("modify get");
    }

    // 게시물 수청 처리
    @PostMapping("/modify")
    public void modify(Article article) {
        log.info("modify post");
    }

    // 게시물 삭제 처리
    @PostMapping("/remove")
    public String remove(Long articleNo) {
        log.info("remove");
        return "redirect:/articles";
    }

}
