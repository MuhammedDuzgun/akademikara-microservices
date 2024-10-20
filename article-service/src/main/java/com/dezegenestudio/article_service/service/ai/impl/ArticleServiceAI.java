package com.dezegenestudio.article_service.service.ai.impl;

import com.dezegenestudio.article_service.entity.Article;
import com.dezegenestudio.article_service.service.ai.IArticleServiceAI;
import com.dezegenestudio.article_service.utils.Prompts;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceAI implements IArticleServiceAI {
    private final ChatClient chatClient;

    public ArticleServiceAI(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem(Prompts.articlePrompt)
                .build();
    }

    @Override
    public List<Article> getRelatedArticles(String subject) {
        return chatClient.prompt()
                .user(u->u.text("Bana {subject}  hakkındaki en önemli 2 bilimsel makaleyi verir misin").param("subject", subject))
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }
}
