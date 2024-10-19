package com.dezegenestudio.article_service.controller;

import com.dezegenestudio.article_service.model.Article;
import com.dezegenestudio.article_service.service.IArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getRelatedArticles(@RequestParam String subject) {
        return articleService.getRelatedArticles(subject);
    }

}
