package com.doubles.bootdemo9.reply.persistence;

import com.doubles.bootdemo9.reply.domain.Reply;
import org.springframework.data.repository.CrudRepository;

public interface ReplyRepository extends CrudRepository<Reply, Long> {
}
