package com.doubles.myboot09.persistence;

import com.doubles.myboot09.domain.QWebBoard;
import com.doubles.myboot09.domain.WebBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard> {

    // 페이징 처리, 검색조건 처리
    public default Predicate makePredicate(String type, String keyword) {

        BooleanBuilder builder = new BooleanBuilder();

        QWebBoard board = QWebBoard.webBoard;

        builder.and(board.bno.gt(0));
        // type if ~ else
        if ( type == null ) {
            return builder;
        }
        switch (type) {
            case "t":
                builder.and(board.title.like("%" + keyword + "%"));
                break;
            case "c":
                builder.and(board.content.like("%" + keyword + "%"));
            case "w":
                builder.and(board.writer.like("%" + keyword + "%"));
        }

        return builder;
    }

}
