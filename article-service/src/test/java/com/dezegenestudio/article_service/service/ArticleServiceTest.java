package com.dezegenestudio.article_service.service;

import com.dezegenestudio.article_service.dto.ArticleDto;

import com.dezegenestudio.article_service.dto.SaveArticleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private IArticleService articleService;

    @Test
    public void addArticleTest() {
        //create article
        SaveArticleDto articleDto = new SaveArticleDto("deneme-1","deneme-1","deneme-1");
        SaveArticleDto createdArticle = articleService.addArticle(articleDto);
        assert createdArticle != null;
    }

    @Test
    public void deleteArticle() {
        articleService.deleteArticle(2L);
    }

}
