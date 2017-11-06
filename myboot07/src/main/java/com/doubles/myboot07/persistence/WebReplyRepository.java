package com.doubles.myboot07.persistence;

import com.doubles.myboot07.domain.WebBoard;
import com.doubles.myboot07.domain.WebReply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WebReplyRepository extends CrudRepository<WebReply, Long> {

    // 댓글 등록 후 목록 처리
    @Query("SELECT r FROM WebReply r WHERE r.board = ?1 AND r.rno > 0 ORDER BY r.rno ASC")
    public List<WebReply> getRepliesOfBoard(WebBoard board);
}
