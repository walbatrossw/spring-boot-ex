package com.doubles.bootdemo9;

import com.doubles.bootdemo9.article.domain.Article;
import com.doubles.bootdemo9.reply.domain.Reply;
import com.doubles.bootdemo9.reply.persistence.ReplyRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.stream.IntStream;


@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
@Log
public class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    // 댓글 입력 테스트
    @Test
    public void repliesInsert() {

        Long[] replyNos = {999L, 998L, 997L, 996L, 995L, 994L};

        Arrays.stream(replyNos).forEach(num -> {

            Article article = new Article();

            article.setArticleNo(num);

            IntStream.range(0, 30).forEach(i -> {

                Reply reply = new Reply();
                reply.setContent("샘플 댓글 내용입니다... 좋은 글 감사합니다... ");
                reply.setWriter("회원" + (i % 10));
                reply.setArticle(article);
                replyRepository.save(reply);
            });

        });

    }

}
