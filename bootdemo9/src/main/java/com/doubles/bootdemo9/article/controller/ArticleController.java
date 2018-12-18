package com.doubles.bootdemo9.article.controller;

import com.doubles.bootdemo9.article.domain.Article;
import com.doubles.bootdemo9.article.persistence.ArticleRepository;
import com.doubles.bootdemo9.article.vo.PageMaker;
import com.doubles.bootdemo9.article.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public void list(PageVO pageVO, Model model) {


        Pageable pageable = pageVO.makePageable(0, "articleNo");
        Page<Article> result = articleRepository.findAll(articleRepository.makePredicate(null, null), pageable);

        log.info("list() called ...");
        log.info("" + pageable);
        log.info("" + result);
        log.info("total page number : " + result.getTotalPages());

        model.addAttribute("result", new PageMaker<>(result));

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
