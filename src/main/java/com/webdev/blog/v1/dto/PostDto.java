package com.webdev.blog.v1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;

    @NotEmpty
    @Min(value = 2, message = "Длинна заголовка должна быть не короче 2 символов")
    private String title;

    @NotEmpty
    @Min(value = 10, message = "Длинна описания поста должна быть не короче 10 символов")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
