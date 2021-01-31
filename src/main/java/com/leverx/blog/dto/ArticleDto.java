package com.leverx.blog.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ArticleDto {
    private String title;
    private String text;
    private Long authorId;
    private Set<String> tags;
}

