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
    public void list(PageVO pageVO, Model model) {

        Pageable pageable = pageVO.makePageable(0, "articleNo");
        Page<Article> result = articleRepository.findAll(articleRepository.makePredicate(pageVO.getType(), pageVO.getKeyword()), pageable);

        log.info("list() called ...");
        log.info("" + pageVO);
        log.info("" + pageable);
        log.info("" + result);
        log.info("total page number : " + result.getTotalPages());

        model.addAttribute("result", new PageMaker<>(result));

    }

    // 게시물 작성 페이지
    @GetMapping("/write")
    public void write(@ModelAttribute("article") Article article) {

        log.info("write() get called ...");

    }

    // 게시물 작성 처리
    @PostMapping("/write")
    public String write(@ModelAttribute("article") Article article, RedirectAttributes redirectAttributes) {

        log.info("write() post called ...");
        log.info("" + article);

        articleRepository.save(article);
        redirectAttributes.addFlashAttribute("msg", "write success");

        return "redirect:/article/list";
    }

    // 게시물 조회 페이지
    @GetMapping("/read")
    public void read(Long articleNo, @ModelAttribute("pageVO") PageVO pageVO, Model model) {

        log.info("read() get called ...");
        log.info("" + articleNo);

        articleRepository.findById(articleNo).ifPresent(article -> model.addAttribute("article", article));

    }

    // 게시물 수정 페이지
    @GetMapping("/modify")
    public void modify(Long articleNo, @ModelAttribute("pageVO") PageVO pageVO, Model model) {

        log.info("modify() get called ...");
        log.info("" + articleNo);

        articleRepository.findById(articleNo).ifPresent(article -> model.addAttribute("article", article));

    }

    // 게시물 수정 처리
    @PostMapping("/modify")
    public String modify(Article article, PageVO pageVO, RedirectAttributes redirectAttributes) {

        log.info("modify() post called ...");
        log.info("modify article : " + article);

        articleRepository.findById(article.getArticleNo()).ifPresent(origin -> {

            origin.setTitle(article.getTitle());
            origin.setContent(article.getContent());
            articleRepository.save(origin);

            redirectAttributes.addFlashAttribute("msg", "modify success");
            redirectAttributes.addAttribute("articleNo", article.getArticleNo());

        });

        getRedirectAttributes(pageVO, redirectAttributes);

        return "redirect:/article/read";
    }

    // 게시물 삭제 처리
    @PostMapping("/remove")
    public String remove(Long articleNo, PageVO pageVO, RedirectAttributes redirectAttributes) {

        log.info("remove() post called ...");
        log.info("remove article : " + articleNo);

        articleRepository.deleteById(articleNo);
        redirectAttributes.addFlashAttribute("msg", "remove success");

        getRedirectAttributes(pageVO, redirectAttributes);

        return "redirect:/article/list";
    }

    private void getRedirectAttributes(PageVO pageVO, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("page", pageVO.getPage());
        redirectAttributes.addAttribute("size", pageVO.getSize());
        redirectAttributes.addAttribute("type", pageVO.getType());
        redirectAttributes.addAttribute("keyword", pageVO.getKeyword());
    }

}
