package com.dezegenestudio.article_service.service;

import com.dezegenestudio.article_service.model.Article;

import java.util.List;

public interface IArticleService {
    List<Article> getRelatedArticles(String subject);
}
