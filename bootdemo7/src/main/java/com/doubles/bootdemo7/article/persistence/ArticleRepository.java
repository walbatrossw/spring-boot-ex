package com.doubles.bootdemo7.article.persistence;

import com.doubles.bootdemo7.article.domain.Article;
import com.doubles.bootdemo7.article.domain.QArticle;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;



public interface ArticleRepository extends CrudRepository<Article, Long>, QuerydslPredicateExecutor<Article> {

    public default Predicate makePredicate(String type, String keyword) {

        BooleanBuilder builder = new BooleanBuilder();

        QArticle article = QArticle.article;

        builder.and(article.articleNo.gt(0));

        if (type == null) {
            return builder;
        }

        switch (type) {
            case "t":
                builder.and(article.title.like("%" + keyword + "%"));
                break;
            case "c":
                builder.and(article.content.like("%" + keyword + "%"));
                break;
            case "w":
                builder.and(article.writer.like("%" + keyword + "%"));
                break;
        }

        return builder;
    }

}
