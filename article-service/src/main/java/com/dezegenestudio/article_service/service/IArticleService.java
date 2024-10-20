package com.dezegenestudio.article_service.service;

import com.dezegenestudio.article_service.dto.ArticleDto;
import com.dezegenestudio.article_service.dto.SaveArticleDto;

public interface IArticleService {
    SaveArticleDto addArticle(SaveArticleDto articleDto);
    void deleteArticle(Long id);
}
