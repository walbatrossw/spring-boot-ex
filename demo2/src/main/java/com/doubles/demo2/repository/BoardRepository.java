package com.doubles.demo2.repository;

import com.doubles.demo2.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
    
}
