package com.webdev.blog.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "RegisterDto Information"
)
public class RegisterDto {
    @Schema(
            description = "The Name during registration"
    )
    private String name;

    @Schema(
            description = "The UserName during registration"
    )
    private String username;

    @Schema(
            description = "The Email during registration"
    )
    private String email;

    @Schema(
            description = "The Password during registration"
    )
    private String password;
}
