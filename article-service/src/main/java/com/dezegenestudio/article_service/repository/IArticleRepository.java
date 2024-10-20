package com.dezegenestudio.article_service.repository;

import com.dezegenestudio.article_service.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<Article, Long> {
    public Article findByTitleAndAuthorAndDate(String title, String author, String date);
}
