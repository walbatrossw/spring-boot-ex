package com.doubles.bootdemo2;

import com.doubles.bootdemo2.domain.Board;
import com.doubles.bootdemo2.persistence.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    // repository 확인
    @Test
    public void inspect() {
        Class<?> clz = boardRepository.getClass();

        System.out.println(clz.getName());

        Class<?>[] interfaces = clz.getInterfaces();

        Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));

        Class<?> superClass = clz.getSuperclass();

        System.out.println(superClass.getName());
    }

    // 입력 테스트
    @Test
    public void insert() {
        Board board = new Board();
        board.setTitle("게시물 제목");
        board.setContent("게시물 내용 넣기...");
        board.setWriter("user00");
        boardRepository.save(board);
    }

    // 조회 테스트
    @Test
    public void read() {
        Board board = boardRepository.findOne(1L);
        System.out.println(board);
    }

    // 수정테스트
    @Test
    public void update() {

        System.out.println("Read First...");
        Board board = boardRepository.findOne(1L);

        System.out.println("Update Title...");
        board.setTitle("게시물 수정 제목...");

        System.out.println("Save...");
        boardRepository.save(board);

    }

    // 삭제 테스트
    @Test
    public void delete() {
        System.out.println("Delete Entity...");
        boardRepository.delete(1L);
    }



}
