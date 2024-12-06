package com.webdev.blog.v1.dto;

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
public class CommentDto {
    private Long id;
    @NotEmpty(message = "Это поле обязательно для заполнения")
    @Min(value = 2, message = "Это поле не должно быть короче 2 символов")
    private String name;

    @NotEmpty(message = "Это поле обязательно для заполнения")
    @Email(message = "Не корректный адрес электронной почты")
    private String email;

    @NotEmpty(message = "Это поле обязательно для заполнения")
    private String body;

}
