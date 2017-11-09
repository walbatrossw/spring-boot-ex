package com.doubles.myboot09.persistence;

import com.doubles.myboot09.domain.WebBoard;
import org.springframework.data.repository.CrudRepository;

public interface CustomCrudRepository extends CrudRepository<WebBoard, Long>, CustomWebBoard {
}
