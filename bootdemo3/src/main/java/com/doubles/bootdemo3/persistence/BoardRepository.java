package com.doubles.bootdemo3.persistence;

import com.doubles.bootdemo3.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {

}
