package com.webdev.blog.v1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Длинна заголовка должна быть не короче 2 символов")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Длинна описания поста должна быть не короче 10 символов")
    private String content;

    @NotEmpty
    private String description;
    private List<CommentDto> comments;
}
