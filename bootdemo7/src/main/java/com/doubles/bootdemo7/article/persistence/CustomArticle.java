package com.doubles.bootdemo7.article.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomArticle {

    public Page<Object[]> getCustomPage(String type, String keyword, Pageable page);

}
