package com.leverx.blog.controller;

import com.leverx.blog.dto.CommentDto;
import com.leverx.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles/{articleId}/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentDto createComment(@RequestBody CommentDto comment,
                                    @PathVariable Long articleId) {
        commentService.createComment(comment, articleId);
        return comment;
    }

}
