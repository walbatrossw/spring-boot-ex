package com.doubles.bootdemo3;

import com.doubles.bootdemo3.domain.Board;
import com.doubles.bootdemo3.domain.QBoard;
import com.doubles.bootdemo3.persistence.BoardRepository;
import com.querydsl.core.BooleanBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Bootdemo3ApplicationTests {

	@Autowired
	private BoardRepository boardRepository;

	/*쿼리 메서드 테스트*/

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
	public void testFindBoardByTitle() {
		boardRepository.findBoardByTitle("제목...177")
				.forEach(board -> System.out.println(board));
	}

	// 게시글 작성자로 조회 테스트
	@Test
	public void testByWriter() {
//		Collection<Board> results = boardRepository.findByWriter("user00");
//		results.forEach(board -> System.out.println(board));
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
		//Collection<Board> results = boardRepository.findByBnoGreaterThan(0L, paging);
		//results.forEach(board -> System.out.println(board));
	}

	// 페이징, 정렬
	@Test
	public void testBnoPagingSort2() {
        Pageable paging = new PageRequest(0, 10, Sort.Direction.ASC, "bno");
        Page<Board> resulte = boardRepository.findByBnoGreaterThan(0L, paging);
        System.out.println("PAGE SIZE : " + resulte.getSize());
        System.out.println("TOTAL PAGES : " + resulte.getTotalPages());
        System.out.println("TOTAL PAGE : " + resulte.getTotalElements());
        System.out.println("NEXT : " + resulte.nextPageable());

        List<Board> list = resulte.getContent();

        list.forEach(board -> System.out.println(board));

	}

    /*@Query 애너테이션 테스트*/

    // 제목에 대한 검색처리
    @Test
    public void testByTitle() {
        boardRepository.findByTitle("17").forEach(board -> System.out.println(board));
    }

    // 내용에 대한 검색처리 @Param
    @Test
    public void testByContent() {
        boardRepository.findByContent("17").forEach(board -> System.out.println(board));
    }

    // 작성자에 대한 검색처리 #{#entity}
    @Test
    public void testByWriter2() {
        boardRepository.findByWriter("17").forEach(board -> System.out.println(board));
    }

    // 필요한 칼럼만 추출하는 경우
    @Test
    public void testByTitle2() {
        boardRepository.findByTitle2("17").forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    // nativeQuery 사용하는 경우
	@Test
	public void testByTitle3() {
    	boardRepository.findByTitle3("17").forEach(arr -> System.out.println(Arrays.toString(arr)));
	}

	// @Query와 Paging 처리/정렬
	@Test
	public void testByPaging() {
    	Pageable pageable = new PageRequest(0, 10);
    	boardRepository.findByPage(pageable).forEach(board -> System.out.println(board));
	}

	/*Predicate 생성 및 테스트*/
	@Test
	public void testPredicate() {

		String type = "t";
		String keyword = "17";

		BooleanBuilder builder = new BooleanBuilder();	// 동적쿼리에 필요한 조건 추가를 위한 빌더 객체 생성
		QBoard board = QBoard.board;

		if (type.equals("t")) {
			builder.and(board.title.like("%" + keyword + "%"));
		}

		// bno > 0
		builder.and(board.bno.gt(0L));

		Pageable pageable = new PageRequest(0, 10);

		Page<Board> result = boardRepository.findAll(builder, pageable);
		System.out.println("PAGE SIZE : " + result.getSize());
		System.out.println("TOTAL PAGES : " + result.getTotalPages());
		System.out.println("TOTAL COUNT : " + result.getTotalElements());
		System.out.println("NEXT : " + result.nextPageable());

		List<Board> list = result.getContent();
		list.forEach(b -> System.out.println(b));

	}

}
