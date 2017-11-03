package com.doubles.myboot06.controller;

import com.doubles.myboot06.domain.WebBoard;
import com.doubles.myboot06.persistence.WebBoardRepository;
import com.doubles.myboot06.vo.PageMaker;
import com.doubles.myboot06.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards/")
@Log
public class WebBoardController {

    @Autowired
    private WebBoardRepository repo;


    // @PageableDefault 를 이용한 페이지 처리
//    @GetMapping("/list")
//    public void list(@PageableDefault(direction = Sort.Direction.DESC, sort = "bno", size = 10, page = 0) Pageable page) {
//        log.info("list() called..." + page);
//    }

    // PageVo를 생성하는 방식
    @GetMapping("/list")
    public void list(PageVO vo, Model model) {

        Pageable page = vo.makePageable(0, "bno");

        Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), page);
        log.info("" + page);
        log.info("" + result);

        log.info("TOTAL PAGE NUMBER : " + result.getTotalPages());

        //model.addAttribute("result", result);
        model.addAttribute("result", new PageMaker(result));
    }
}
