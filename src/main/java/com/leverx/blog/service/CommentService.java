package com.leverx.blog.service;

import com.leverx.blog.dto.CommentDto;
import com.leverx.blog.model.Comment;

import java.util.List;

public interface CommentService {
//    Comment addCommentByArticleId(Long id);

    CommentDto createComment(CommentDto comment, Long articleId);

    List<Comment> getCommentByArticleId(Long id);

    Comment getComment(Long id);

    void deleteComment(Long id);
}
