package com.leverx.blog.repository;

import com.leverx.blog.model.Article;
import com.leverx.blog.model.Status;
import com.leverx.blog.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    Optional<Article> findByArticleId(Long id);

    List<Article> findArticlesByStatus(Status status);

    List<Article> findArticlesByAuthorUserId(Long id);

    List<Article> findArticlesByTagsContains(Tag tag);
}
