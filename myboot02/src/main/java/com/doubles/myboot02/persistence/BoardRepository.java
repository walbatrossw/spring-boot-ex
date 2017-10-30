package com.doubles.myboot02.persistence;

import com.doubles.myboot02.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
}
