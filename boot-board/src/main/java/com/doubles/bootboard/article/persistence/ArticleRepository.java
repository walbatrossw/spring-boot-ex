package com.doubles.bootboard.article.persistence;

import com.doubles.bootboard.article.domain.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
