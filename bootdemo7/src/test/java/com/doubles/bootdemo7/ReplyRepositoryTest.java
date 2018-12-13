package com.doubles.bootdemo7;

import com.doubles.bootdemo7.article.domain.Article;
import com.doubles.bootdemo7.reply.domain.Reply;
import com.doubles.bootdemo7.reply.persistence.ReplyRepository;
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
@Log
@Commit
public class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void testInsertReplies() {
        Long[] arr = {999L, 998L, 997L};

        Arrays.stream(arr).forEach(num -> {
            Article article = new Article();
            article.setArticleNo(num);

            IntStream.range(0, 10).forEach(i -> {
                Reply reply = new Reply();
                reply.setContent("REPLY .... " + i);
                reply.setWriter("writer"+i);
                reply.setArticle(article);
                replyRepository.save(reply);
            });
        });
    }

}
