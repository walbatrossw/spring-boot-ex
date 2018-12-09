package com.doubles.bootdemo6.board.controller;

import com.doubles.bootdemo6.board.domain.WebBoard;
import com.doubles.bootdemo6.board.persistence.WebBoardRepository;
import com.doubles.bootdemo6.board.vo.PageMaker;
import com.doubles.bootdemo6.board.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    private final WebBoardRepository webBoardRepository;

    @Autowired
    public WebBoardController(WebBoardRepository webBoardRepository) {
        this.webBoardRepository = webBoardRepository;
    }

    // 게시물 목록 : @PageableDefault 애너테이션 사용
//    @GetMapping("/list")
//    public void list(@PageableDefault(direction = Sort.Direction.DESC, sort = "bno", size = 10, page = 0) Pageable page) {
//        log.info("list() + @PageableDefault called..." + page);
//    }

    // 게시물 목록 : PageVO 생성방식
//    @GetMapping("/list")
//    public void list(PageVO pageVO) {
//        Pageable page = pageVO.makePageable(0, "bno");
//        log.info("list() + PageVO called ... " + page);
//
//    }

    // 게시물 목록
    @GetMapping("/list")
    public void list(@ModelAttribute("pageVO") PageVO pageVO, Model model) {

        Pageable page = pageVO.makePageable(0, "bno");
        Page<WebBoard> result = webBoardRepository.findAll(
                webBoardRepository.makePredicate(pageVO.getType(), pageVO.getKeyword()), page);

        log.info("" + page);
        log.info("" + result);

        log.info("TOTAL PAGE NUMBER : " + result.getTotalPages());

        model.addAttribute("result", new PageMaker<>(result));
    }

    // 게시물 작성 페이지
    @GetMapping("/write")
    public void write(@ModelAttribute("webBoard") WebBoard webBoard) {
        log.info("write get");
    }

    // 게시물 작성 처리
    @PostMapping("/write")
    public String write(@ModelAttribute("webBoard") WebBoard webBoard, RedirectAttributes redirectAttributes) {

        log.info("write post");
        log.info("" + webBoard);

        webBoardRepository.save(webBoard);
        redirectAttributes.addFlashAttribute("msg", "write success");

        return "redirect:/boards/list";
    }

    // 게시물 조회
    @GetMapping("/read")
    public void read(Long bno, @ModelAttribute("pageVO") PageVO pageVO, Model model) {
        log.info("BNO:" + bno);
        webBoardRepository.findById(bno).ifPresent(webBoard -> model.addAttribute("webBoard", webBoard));
    }
}
