package com.leverx.blog.service;

import com.leverx.blog.dto.ArticleDto;

import java.util.Set;

public interface ArticleService {
    ArticleDto updateArticle(ArticleDto article, Long id);

    ArticleDto createArticle(ArticleDto article);

    Set<ArticleDto> getPublicArticles();

    void deleteArticle(Long id);

    ArticleDto publishArticle(Long articleId);
}
