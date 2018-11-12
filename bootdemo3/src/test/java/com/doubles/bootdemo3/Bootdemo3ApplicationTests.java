package com.doubles.bootdemo3;

import com.doubles.bootdemo3.domain.Board;
import com.doubles.bootdemo3.persistence.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Bootdemo3ApplicationTests {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	public void insert() {
		for (int i = 1; i <= 200; i++) {
			Board board = new Board();
			board.setTitle("제목..." + i);
			board.setContent("내용..." + i);
			board.setWriter("user0" + (i%10));
			boardRepository.save(board);
		}
	}


}
