package com.doubles.bootdemo3;

import com.doubles.bootdemo3.domain.Board;
import com.doubles.bootdemo3.persistence.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Bootdemo3ApplicationTests {

	@Autowired
	private BoardRepository boardRepository;

	// 입력 테스트
	@Test
	public void testInsert() {
		for (int i = 1; i <= 200; i++) {
			Board board = new Board();
			board.setTitle("제목..." + i);
			board.setContent("내용..." + i);
			board.setWriter("user0" + (i%10));
			boardRepository.save(board);
		}
	}

	// 게시글 제목으로 조회 테스트
	@Test
	public void testByTitle() {
		boardRepository.findBoardByTitle("제목...177")
				.forEach(board -> System.out.println(board));
	}

	// 게시글 작성자로 조회 테스트
	@Test
	public void testByWriter() {
		Collection<Board> results = boardRepository.findByWriter("user00");
		results.forEach(board -> System.out.println(board));
	}

	// like 구문 처리 테스트
	@Test
	public void testByWriterContaining() {
		Collection<Board> results = boardRepository.findByWriterContaining("05");
		results.forEach(board -> System.out.println(board));
	}

	// and or 조건처리 테스트
	@Test
	public void testAndOr() {
		Collection<Board> results = boardRepository.findByTitleContainingOrContentContaining("02", "내용");
		results.forEach(board -> System.out.println(board));
	}

	// 부등호 처리 테스트
	@Test
	public void testByTitleAndBno() {
		Collection<Board> results = boardRepository.findByTitleContainingAndBnoGreaterThan("5", 50L);
		results.forEach(board -> System.out.println(board));
	}

	// order by 처리 테스트
	@Test
	public void testBnoOrderBy() {
		Collection<Board> results = boardRepository.findByBnoGreaterThanOrderByBnoDesc(90L);
		results.forEach(board -> System.out.println(board));
	}

	// 페이징
	@Test
	public void testBnoOrderByPaging() {
		Pageable paging = new PageRequest(0, 10);
		Collection<Board> results = boardRepository.findByBnoGreaterThanOrderByBnoDesc(0L, paging);
		results.forEach(board -> System.out.println(board));
	}

	// 페이징, 정렬
	@Test
	public void testBnoPagingSort() {
		Pageable paging = new PageRequest(0, 10, Sort.Direction.ASC, "bno");
		Collection<Board> results = boardRepository.findByBnoGreaterThan(0L, paging);
		results.forEach(board -> System.out.println(board));
	}

	// 페이징, 정렬
	@Test
	public void testBnoPagingSort2() {

	}
}
