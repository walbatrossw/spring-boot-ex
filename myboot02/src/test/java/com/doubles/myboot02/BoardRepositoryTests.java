package com.doubles.myboot02;


import com.doubles.myboot02.domain.Board;
import com.doubles.myboot02.persistence.BoardRepository;
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

    @Test
    public void inspect() {

        // 실제 객체의 클래스 이름
        Class<?> clz = boardRepository.getClass();
        System.out.println(clz.getName());

        // 클래스가 구현하고 있는 인터페이스 목록
        Class<?>[] interfaces = clz.getInterfaces();
        Stream.of(interfaces).forEach(inter -> System.out.println(inter.getName()));

        // 클래스의 부모 클래스
        Class<?> superClasses = clz.getSuperclass();
        System.out.println(superClasses.getName());

    }

    // 입력
    @Test
    public void testInsert() {
        Board board = new Board();
        board.setTitle("제목..");
        board.setContent("내용...");
        board.setWriter("홍길동");
        boardRepository.save(board);
    }

    // 조회
    @Test
    public void testRead() {
        Board board = boardRepository.findOne(1L);
        System.out.println(board);
    }

    // 수정
    @Test
    public void testUpdate() {
        System.out.println("Read First...");
        Board board = boardRepository.findOne(1L);

        System.out.println("Update Title...");
        board.setTitle("수정된 제목...");

        System.out.println("Call Save...");
        boardRepository.save(board);
    }

    // 삭제
    @Test
    public void testDelete() {
        System.out.println("Delete Entity...");
        boardRepository.delete(1L);
    }
}
