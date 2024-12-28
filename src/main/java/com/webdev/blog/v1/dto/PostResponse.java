package com.webdev.blog.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "PostResponse Information"
)
public class PostResponse {

    @Schema(
            description = "Contains All Posts"
    )
    private List<PostDto> content;

    @Schema(
            description = "Indicates the page number during pagination"
    )
    private int pageNo;

    @Schema(
            description = "Indicates the page size during pagination"
    )
    private int pageSize;

    @Schema(
            description = "Indicates the total number of Posts"
    )
    private long totalElements;

    @Schema(
            description = "Shows the total number of pages during pagination"
    )
    private int totalPages;

    @Schema(
            description = "Indicates whether the page is the latest"
    )
    private boolean last;
}
