package com.dezegenestudio.article_service.service.ai;

import com.dezegenestudio.article_service.entity.Article;

import java.util.List;

public interface IArticleServiceAI {
    List<Article> getRelatedArticles(String subject);
}
