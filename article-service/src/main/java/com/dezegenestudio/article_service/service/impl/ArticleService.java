package com.dezegenestudio.article_service.service.impl;

import com.dezegenestudio.article_service.model.Article;
import com.dezegenestudio.article_service.service.IArticleService;
import com.dezegenestudio.article_service.utils.Prompts;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements IArticleService {
    private final ChatClient chatClient;

    public ArticleService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem(Prompts.articlePrompt)
                .build();
    }

    @Override
    public List<Article> getRelatedArticles(String subject) {
        return chatClient.prompt()
                .user(u->u.text("Bana {subject}  hakkındaki en önemli 5 bilimsel makaleyi verir misin").param("subject", subject))
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }
}
