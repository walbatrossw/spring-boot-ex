package com.doubles.bootdemo3.persistence;

import com.doubles.bootdemo3.domain.Board;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {

    // findBoardBy
    public List<Board> findBoardByTitle(String title);

    // findBy
    public Collection<Board> findByWriter(String writer);

    // like 구문
    public Collection<Board> findByWriterContaining(String writer);

    // and or 조건
    public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

    // 부등호 처리
    public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

    // Order by
    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    // bno > ? order by bno desc limit ?, ?
    public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);

    // bno > ?
    public List<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    //
    //public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);
}
