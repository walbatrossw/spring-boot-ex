package com.doubles.bootdemo7.article.persistence;

import com.doubles.bootdemo7.article.domain.Article;
import com.doubles.bootdemo7.article.domain.QArticle;
import com.doubles.bootdemo7.reply.domain.QReply;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;

@Log
public class CustomCrudRepositoryImpl extends QuerydslRepositorySupport implements CustomArticle {

    public CustomCrudRepositoryImpl() {
        super(Article.class);
    }

    @Override
    public Page<Object[]> getCustomPage(String type, String keyword, Pageable page) {

        log.info("=========================================");
        log.info("TYPE : " + type);
        log.info("KEYWORD : " + keyword);
        log.info("PAGE : " + page);
        log.info("=========================================");

        QArticle article = QArticle.article;
        QReply reply = QReply.reply;

        JPQLQuery<Article> query = from(article);
        JPQLQuery<Tuple> tuple = query.select(article.articleNo, article.title, article.writer, article.regDate, reply.count());

        tuple.leftJoin(reply);
        tuple.on(article.articleNo.eq(reply.article.articleNo));
        tuple.where(article.articleNo.gt(0L));

        if (type != null) {
            switch (type.toLowerCase()) {
                case "t":
                    tuple.where(article.title.like("%" + keyword + "%"));
                    break;
                case "c":
                    tuple.where(article.content.like("%" + keyword + "%"));
                    break;
                case "w":
                    tuple.where(article.writer.like("%" + keyword + "%"));
                    break;
            }
        }

        tuple.groupBy(article.articleNo);
        tuple.orderBy(article.articleNo.desc());

        tuple.offset(page.getOffset());
        tuple.limit(page.getPageSize());

        List<Tuple> list = tuple.fetch();
        List<Object[]> resultList = new ArrayList<>();

        list.forEach(t -> {
            resultList.add(t.toArray());
        });

        long total = tuple.fetchCount();

        return new PageImpl<>(resultList, page, total);
    }
}
