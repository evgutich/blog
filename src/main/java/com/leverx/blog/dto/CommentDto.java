package com.leverx.blog.dto;

import lombok.Data;

@Data
public class CommentDto {
    private String text;
    private Long authorId;
}
