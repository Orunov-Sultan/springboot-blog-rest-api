package com.webdev.blog.v1.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
}
