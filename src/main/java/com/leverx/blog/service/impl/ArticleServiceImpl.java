package com.leverx.blog.service.impl;

import com.leverx.blog.dto.ArticleDto;
import com.leverx.blog.exception.ArticleNotFoundException;
import com.leverx.blog.exception.InsufficientPermissionException;
import com.leverx.blog.exception.UserNotFoundException;
import com.leverx.blog.model.Article;
import com.leverx.blog.model.Status;
import com.leverx.blog.model.Tag;
import com.leverx.blog.model.User;
import com.leverx.blog.repository.ArticleRepository;
import com.leverx.blog.repository.UserRepository;
import com.leverx.blog.service.ArticleService;
import com.leverx.blog.service.SecurityService;
import com.leverx.blog.service.TagService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    private final TagService tagService;

    private final SecurityService securityService;

    public ArticleServiceImpl(ArticleRepository articleRepository,
                              UserRepository userRepository,
                              TagService tagService, SecurityService securityService) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.tagService = tagService;
        this.securityService = securityService;
    }

    @Override
    public ArticleDto updateArticle(ArticleDto article, Long articleId) {
        Article updatedArticle = articleRepository.findByArticleId(articleId).orElseThrow(ArticleNotFoundException::new);
        validateArticleOwner(updatedArticle);
        populateArticleFromDto(article, updatedArticle);
        articleRepository.save(updatedArticle);
        return article;
    }

    @Override
    public ArticleDto createArticle(ArticleDto article) {
        Article createdArticle = new Article();
        populateArticleFromDto(article, createdArticle);
        createdArticle.setStatus(Status.DRAFT);
        articleRepository.save(createdArticle);
        return article;
    }

    @Override
    public Set<ArticleDto> getPublicArticles() {
        return articleRepository.findArticlesByStatus(Status.PUBLIC).stream()
                .map(this::populateDtoFromArticle)
                .collect(toSet());
    }

    @Override
    public void deleteArticle(Long id) {
        Article article = articleRepository.findByArticleId(id).orElseThrow(ArticleNotFoundException::new);
        validateArticleOwner(article);
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> getArticlesByAuthorId(Long authorId) {
        return articleRepository.findArticlesByAuthorUserId(authorId);
    }

    @Override
    public Set<Article> getArticlesByTags(Set<Tag> tags) {
        Set<Article> articles = new HashSet<>();
        for (Tag t : tags) {
            articles.addAll(articleRepository.findArticlesByTagsContains(t));
        }
        return articles;
    }

    @Override
    public ArticleDto publishArticle(Long articleId) {
        Article publishArticle = articleRepository.findById(articleId).orElseThrow(ArticleNotFoundException::new);
        validateArticleOwner(publishArticle);
        publishArticle.setStatus(Status.PUBLIC);
        articleRepository.save(publishArticle);
        return populateDtoFromArticle(publishArticle);
    }

    private void populateArticleFromDto(ArticleDto articleDto, Article article) {
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        User author = userRepository.findById(articleDto.getAuthorId()).orElseThrow(UserNotFoundException::new);
        article.setAuthor(author);
        populateTags(articleDto, article);
    }

    private void populateTags(ArticleDto articleDto, Article article) {
        Set<Tag> tags = articleDto.getTags().stream()
                .map(tagService::getOrCreateByName)
                .collect(toSet());
        article.setTags(tags);
    }

    private ArticleDto populateDtoFromArticle(Article article){
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle(article.getTitle());
        articleDto.setText(article.getText());
        articleDto.setTags(article.getTags().stream().map(Tag::getName).collect(toSet()));
        articleDto.setAuthorId(article.getAuthor().getUserId());
        return articleDto;
    }

    private void validateArticleOwner(Article article) {
        Long userId = securityService.getLoggedInUser().getUserId();
        if (!userId.equals(article.getAuthor().getUserId())) {
            throw new InsufficientPermissionException();
        }
    }
}
