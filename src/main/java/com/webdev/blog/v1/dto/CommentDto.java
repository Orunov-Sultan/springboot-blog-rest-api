package com.webdev.blog.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "CommentDto Information"
)
public class CommentDto {
    private Long id;

    @Schema(
            description = "Post Comment Name"
    )
    @NotEmpty(message = "Это поле обязательно для заполнения")
    @Min(value = 2, message = "Это поле не должно быть короче 2 символов")
    private String name;

    @Schema(
            description = "User email who left the comment"
    )
    @NotEmpty(message = "Это поле обязательно для заполнения")
    @Email(message = "Не корректный адрес электронной почты")
    private String email;

    @Schema(
            description = "The Comment Body"
    )
    @NotEmpty(message = "Это поле обязательно для заполнения")
    private String body;

}
