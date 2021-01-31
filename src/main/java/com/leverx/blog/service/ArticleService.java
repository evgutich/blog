package com.leverx.blog.service;

import com.leverx.blog.dto.ArticleDto;
import com.leverx.blog.model.Article;
import com.leverx.blog.model.Tag;

import java.util.List;
import java.util.Set;

public interface ArticleService {
    ArticleDto updateArticle(ArticleDto article, Long id);

    ArticleDto createArticle(ArticleDto article);

    Set<ArticleDto> getPublicArticles();

    void deleteArticle(Long id);

    List<Article> getArticlesByAuthorId(Long id);

    Set<Article> getArticlesByTags(Set<Tag> tags);

    ArticleDto publishArticle(Long articleId);
}
