package com.webdev.blog.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(
        description = "PostDto Information"
)
public class PostDto {
    private Long id;

    @Schema(
            description = "Blog Post Title"
    )
    @NotEmpty
    @Size(min = 2, message = "Длинна заголовка должна быть не короче 2 символов")
    private String title;

    @Schema(
            description = "Blog Post Content"
    )
    @NotEmpty
    @Size(min = 10, message = "Длинна описания поста должна быть не короче 10 символов")
    private String content;

    @Schema(
            description = "Blog Post Description"
    )
    @NotEmpty
    private String description;
    private List<CommentDto> comments;

    @Schema(
            description = "Blog Post Category"
    )
    private Long categoryId;


}
