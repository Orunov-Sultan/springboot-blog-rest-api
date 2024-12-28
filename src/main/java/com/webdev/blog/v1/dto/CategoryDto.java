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
        description = "CategoryDto Information"
)
public class CategoryDto {
    private Long id;

    @Schema(
            description = "The Category Name"
    )
    private String name;

    @Schema(
            description = "The Category Description"
    )
    private String description;
}
