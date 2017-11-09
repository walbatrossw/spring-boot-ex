package com.doubles.myboot09.controller;


import com.doubles.myboot09.domain.WebBoard;
import com.doubles.myboot09.persistence.CustomCrudRepository;
import com.doubles.myboot09.vo.PageMaker;
import com.doubles.myboot09.vo.PageVO;
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

@Controller
@RequestMapping("/boards")
@Log
public class WebBoardController {

    @Autowired
    private CustomCrudRepository repo;

    @GetMapping("/list")
    public void list(@ModelAttribute("pageVO")PageVO vo, Model model) {

        Pageable page = vo.makePageable(0, "bno");
        Page<Object[]> result = repo.getCustomPage(vo.getType(), vo.getKeyword(), page);

        log.info("" + page);
        log.info("" + result);

        log.info("TOTAL PAGE NUMBER : " + result.getTotalPages());

        model.addAttribute("result", new PageMaker<>(result));
    }

    // 게시물 입력 페이지 매핑
    @GetMapping("/register")
    public void registerGet(@ModelAttribute("vo")WebBoard vo) {
        log.info("register get");
    }

    // 게시물 입력 처리
    @PostMapping("/register")
    public String registerPost(@ModelAttribute("vo")WebBoard vo, RedirectAttributes rttr) {
        log.info("register post");
        log.info("" + vo);

        repo.save(vo);
        rttr.addFlashAttribute("msg", "success");  // URL로는 보이지 않는 문자열 생성
        return "redirect:/boards/list";
    }

    // 게시물 조회
    @GetMapping("/view")
    public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
        log.info("BNO : " + bno);
        repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
    }

    // 게시물 수정 페이지 매핑
    @GetMapping("/modify")
    public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
        log.info("MODIFY BNO : " + bno);
        repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
    }

    // 게시물 수정처리
    @PostMapping("modify")
    public String modifyPost(WebBoard board, PageVO vo, RedirectAttributes rttr) {
        log.info("MODIFY WebBoard : " + board);
        repo.findById(board.getBno()).ifPresent( origin -> {
            origin.setTitle(board.getTitle());
            origin.setContent(board.getContent());

            repo.save(origin);
            rttr.addFlashAttribute("msg", "success");
            rttr.addAttribute("bno", origin.getBno());
        });
        // 페이징과 검색했던 결과로 이동하는 경우
        rttr.addAttribute("page", vo.getPage());
        rttr.addAttribute("size", vo.getSize());
        rttr.addAttribute("type", vo.getType());
        rttr.addAttribute("keyword", vo.getKeyword());

        return "redirect:/boards/view";
    }

    // 게시물 삭제
    @PostMapping("/delete")
    public String delete(Long bno, PageVO vo, RedirectAttributes rttr) {
        log.info("DELETE BNO : " + bno);
        repo.deleteById(bno);
        rttr.addFlashAttribute("msg", "success");
        // 페이징과 검색했던 결과로 이동하는 경우
        rttr.addAttribute("page", vo.getPage());
        rttr.addAttribute("size", vo.getSize());
        rttr.addAttribute("type", vo.getType());
        rttr.addAttribute("keyword", vo.getKeyword());

        return "redirect:/boards/list";
    }
}
