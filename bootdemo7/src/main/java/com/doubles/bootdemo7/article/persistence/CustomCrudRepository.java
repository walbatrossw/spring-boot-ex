package com.doubles.bootdemo7.article.persistence;

import com.doubles.bootdemo7.article.domain.Article;
import org.springframework.data.repository.CrudRepository;

public interface CustomCrudRepository extends CrudRepository<Article, Long>, CustomArticle {

}
