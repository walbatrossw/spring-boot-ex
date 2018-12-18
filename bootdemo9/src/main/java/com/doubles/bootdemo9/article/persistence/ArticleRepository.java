package com.doubles.bootdemo9.article.persistence;

import com.doubles.bootdemo9.article.domain.Article;
import com.doubles.bootdemo9.article.domain.QArticle;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import static com.doubles.bootdemo9.article.domain.QArticle.article;


public interface ArticleRepository extends CrudRepository<Article, Long>, QuerydslPredicateExecutor<Article> {

    // 검색어 페이징 처리
    public default Predicate makePredicate(String type, String keyword) {


        BooleanBuilder builder = new BooleanBuilder();

        QArticle article = QArticle.article;

        // articleNo > 0 조건
        builder.and(article.articleNo.gt(0));

        // 검색어가 없을 경우
        if (type == null) {
            return builder;
        }

        // 검색조건에 따라 처리
        switch (type) {
            case "t" :
                builder.and(article.title.like("%" + keyword + "%"));
                break;
            case "c" :
                builder.and(article.content.like("%" + keyword + "%"));
                break;
            case "w" :
                builder.and(article.writer.like("%" + keyword + "%"));
                break;
        }
        return builder;

    }

}
