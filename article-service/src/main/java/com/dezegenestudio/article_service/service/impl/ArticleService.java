package com.dezegenestudio.article_service.service.impl;

import com.dezegenestudio.article_service.dto.SaveArticleDto;
import com.dezegenestudio.article_service.entity.Article;
import com.dezegenestudio.article_service.repository.IArticleRepository;
import com.dezegenestudio.article_service.service.IArticleService;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ArticleService implements IArticleService {

    private final IArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public List<SaveArticleDto> getAllArticles() {
        List<SaveArticleDto> savedArticles = new ArrayList<>();
        List<Article> articles = articleRepository.findAll();
        for (Article article : articles) {
            SaveArticleDto articleDto = modelMapper.map(article, SaveArticleDto.class);
            savedArticles.add(articleDto);
        }
        return savedArticles;
    }

    @Override
    public SaveArticleDto addArticle(SaveArticleDto articleDto) {
        Article article = articleRepository.findByTitleAndAuthorAndDate(articleDto.getTitle(), articleDto.getAuthor(), articleDto.getDate());
        if (article != null) {
            throw new RuntimeException("Article already exists");
        }
        article = modelMapper.map(articleDto, Article.class);
        articleRepository.save(article);
        return modelMapper.map(article, SaveArticleDto.class);
    }

    @Override
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            articleRepository.delete(article);
        } else {
            throw new RuntimeException("Article with id " + id + " not found");
        }
    }


}
