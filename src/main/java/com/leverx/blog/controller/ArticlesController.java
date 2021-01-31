package com.leverx.blog.controller;

import com.leverx.blog.dto.ArticleDto;
import com.leverx.blog.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/articles")
public class ArticlesController {

    private final ArticleService articleService;


    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public ArticleDto createArticle(@RequestBody ArticleDto article) {
        return articleService.createArticle(article);
    }

    @PutMapping("/{articleId}")
    public ArticleDto updateArticle(@RequestBody ArticleDto article, @PathVariable Long articleId) {
        return articleService.updateArticle(article, articleId);
    }

    @GetMapping
    public Set<ArticleDto> getAllArticles() {
        return articleService.getPublicArticles();
    }

    @PutMapping("/{articleId}/publish")
    public ArticleDto publishArticle(@PathVariable Long articleId) {
        return articleService.publishArticle(articleId);
    }

    @DeleteMapping("/{articleId}")
    public void deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
    }

}
