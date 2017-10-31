package com.doubles.myboot03;

import com.doubles.myboot03.domain.Board;
import com.doubles.myboot03.domain.QBoard;
import com.doubles.myboot03.persistence.BoardRepository;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import com.querydsl.core.BooleanBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Myboot03ApplicationTests {

    @Autowired
    private BoardRepository boardRepository;

    /* ---------------------테스트를 위해 더미 데이터 생성---------------------*/
    // 1. 200개 게시물 작성
    @Test
    public void testInsert200() {
        for (int i = 1; i <= 200; i++) {
            Board board = new Board();
            board.setTitle(i + "번 게시물...");
            board.setContent(i + "번 내용...");
            board.setWriter("user0" + (i % 10));
            boardRepository.save(board);
        }
    }

    /* ---------------------쿼리 메서드 테스트---------------------*/

    // 1. 제목으로 검색 처리 테스트
    @Test
    public void testByTitle() {
//        List<Board> boards = boardRepository.findBoardByTitle("1번 게시물...");
//        for (Board board : boards) {
//            System.out.println(board);
//        }
        boardRepository.findBoardByTitle("2번 게시물...").forEach(board -> System.out.println(board));
    }

    // 2. findBy 특정 칼럼 테스트 : 작성자로 검색처리 테스트
    @Test
    public void testByWriter() {
        Collection<Board> results = boardRepository.findByWriter("user00");
        results.forEach(board -> System.out.println(board));
    }

    // 3. LIKE 구문 처리 테스트
    @Test
    public void testByWriterContaining() {
        Collection<Board> results = boardRepository.findByWriterContaining("03");
        results.forEach(board -> System.out.println(board));
    }

    // OR 조건 처리 테스트
    @Test
    public void testByTitleContainingOrContentContaining() {
        Collection<Board> results = boardRepository.findByTitleContainingOrContentContaining("01", "내용");
        results.forEach(board -> System.out.println(board));
    }

    // AND 조건, 부등호 처리 테스트
    @Test
    public void testByTitleAndBno() {
        Collection<Board> results = boardRepository.findByTitleContainingAndBnoGreaterThan("5", 50L);
        results.forEach(board -> System.out.println(board));
    }

    // ORDER BY 처리 테스트
    @Test
    public void testBnoOrderBy() {
        Collection<Board> results = boardRepository.findByBnoGreaterThanOrderByBnoDesc(90L);
        results.forEach(board -> System.out.println(board));
    }

    // 페이징 처리 테스트
    @Test
    public void testBnoOrderByPaging() {
        Pageable paging = new PageRequest(0, 10);
        Collection<Board> results = boardRepository.findByBnoGreaterThanOrderByBnoDesc(0L, paging);
        results.forEach(board -> System.out.println(board));
    }

    // 페이징 처리 테스트 2
//    @Test
//    public void testBnoPagingSort() {
//        Pageable paging = new PageRequest(0, 10, Sort.Direction.ASC, "bno");
//        Collection<Board> results = boardRepository.findByBnoGreaterThan(0L, paging);
//        results.forEach(board -> System.out.println(board));
//    }

    // 페이징 처리 테스트 3
    @Test
    public void testBnoPagingSort() {
        Pageable paging = new PageRequest(0, 10, Sort.Direction.ASC, "bno");
        Page<Board> result = boardRepository.findByBnoGreaterThan(0L, paging);

        System.out.println("PAGE SIZE: " + result.getSize());
        System.out.println("TOTAL SIZE: " + result.getTotalPages());
        System.out.println("TOTAL COUNT: " + result.getTotalElements());
        System.out.println("NEXT: " + result.nextPageable());

        List<Board> list = result.getContent();
        list.forEach(board -> System.out.println(board));
    }

    /* ---------------------jpql 테스트---------------------*/

    // JPQL 테스트
    @Test
    public void testByTitle2() {
        boardRepository.findByTitle("17").forEach(board -> System.out.println(board));
    }

    // 필요한 칼럼만 추출하는 경우 테스트
    @Test
    public void testByTitle17() {
        boardRepository.findByTitle2("17").forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    // 페이지 처리 테스트 4 (JPQL)
    @Test
    public void testByPaging() {
        Pageable pageable = new PageRequest(0, 10);
        boardRepository.findByPage(pageable).forEach(board -> System.out.println(board));
    }

    /* ---------------------Querydsl 테스트---------------------*/

    @Test
    public void testPredicate() {
        String type = "t";
        String keyword = "17";

        BooleanBuilder builder = new BooleanBuilder();

        QBoard board = QBoard.board;

        if (type.equals("t")) {
            builder.and(board.title.like("%" + keyword + "%"));
        }

        // bno > 0
        builder.and(board.bno.gt(0L));
        Pageable pageable = new PageRequest(0, 10);
        Page<Board> result = boardRepository.findAll(builder, pageable);

        System.out.println("PAGE SIZE: " + result.getSize());
        System.out.println("TOTAL SIZE: " + result.getTotalPages());
        System.out.println("TOTAL COUNT: " + result.getTotalElements());
        System.out.println("NEXT: " + result.nextPageable());

        List<Board> list = result.getContent();
        list.forEach(b -> System.out.println(b));
    }
}
