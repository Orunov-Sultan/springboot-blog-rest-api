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
        description = "LoginDto Information"
)
public class LoginDto {

    @Schema(
            description = "Login or Email address to log in"
    )
    private String usernameOrEmail;

    @Schema(
            description = "Password to log in"
    )
    private String password;
}
