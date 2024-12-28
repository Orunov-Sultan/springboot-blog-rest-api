package com.webdev.blog.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "JWTAuthResponse Information"
)
public class JWTAuthResponse {

    @Schema(
            description = "JWT Token"
    )
    private String accessToken;

    @Schema(
            description = "JWT type"
    )
    private String tokenType = "Dearer";
}
