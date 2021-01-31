package com.leverx.blog.repository;

import com.leverx.blog.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findCommentByAuthorUserId(Long id);
}
