package com.leverx.blog.service.impl;

import com.leverx.blog.dto.CommentDto;
import com.leverx.blog.exception.ArticleNotFoundException;
import com.leverx.blog.exception.CommentNotFoundException;
import com.leverx.blog.exception.UserNotFoundException;
import com.leverx.blog.model.Article;
import com.leverx.blog.model.Comment;
import com.leverx.blog.model.User;
import com.leverx.blog.repository.ArticleRepository;
import com.leverx.blog.repository.CommentRepository;
import com.leverx.blog.repository.UserRepository;
import com.leverx.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, ArticleRepository articleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentDto createComment(CommentDto comment, Long articleId) {
        Comment createdComment = new Comment();
        createdComment.setText(comment.getText());
        Article article = articleRepository.findByArticleId(articleId).orElseThrow(ArticleNotFoundException::new);
        createdComment.setArticle(article);
        User author = userRepository.findById(comment.getAuthorId()).orElseThrow(UserNotFoundException::new);
        createdComment.setAuthor(author);
        commentRepository.save(createdComment);
        return comment;
    }

    @Override
    public List<Comment> getCommentByArticleId(Long id) {
        return commentRepository.findCommentByAuthorUserId(id);
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
