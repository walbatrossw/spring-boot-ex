package com.doubles.bootdemo2.persistence;

import com.doubles.bootdemo2.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {

}
