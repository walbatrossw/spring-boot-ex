package com.doubles.bootdemo7.article.persistence;

import com.doubles.bootdemo7.article.domain.Article;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long>, QuerydslPredicateExecutor<Article> {

}
