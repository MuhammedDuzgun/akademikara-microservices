package com.dezegenestudio.article_service.controller;

import com.dezegenestudio.article_service.dto.SaveArticleDto;
import com.dezegenestudio.article_service.entity.Article;
import com.dezegenestudio.article_service.service.IArticleService;
import com.dezegenestudio.article_service.service.ai.IArticleServiceAI;
import com.dezegenestudio.article_service.service.impl.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final IArticleServiceAI articleServiceAI;
    private final ArticleService articleService;

    @GetMapping
    public List<Article> getRelatedArticles(@RequestParam String subject) {
        return articleServiceAI.getRelatedArticles(subject);
    }

    @PostMapping("/save-article")
    public ResponseEntity<SaveArticleDto> addArticle(@RequestBody SaveArticleDto articleDto) {
        articleService.addArticle(articleDto);
        return ResponseEntity.ok(articleDto);
    }

}
