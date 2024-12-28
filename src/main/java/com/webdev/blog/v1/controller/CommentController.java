package com.webdev.blog.v1.controller;

import com.webdev.blog.v1.dto.CommentDto;
import com.webdev.blog.v1.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@AllArgsConstructor
@Tag(
        name = "CRUD REST APIs for Comment Resource"
)
public class CommentController {
    private CommentService commentService;

    @Operation(
            summary = "Create Comment REST API",
            description = "Create Comment REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @RequestBody @Valid CommentDto commentDto){
        CommentDto comment = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Comment By Post Id REST API",
            description = "Get Comment By Post Id REST API is used to get single from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Long postId){
        List<CommentDto> comments = commentService.getCommentByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @Operation(
            summary = "Get Comment By Id REST API",
            description = "Get Comment By Id REST API is used to get single from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long postId, @PathVariable Long commentId){
        CommentDto comment = commentService.getCommentById(postId, commentId);
        return ResponseEntity.ok(comment);
    }

    @Operation(
            summary = "Update Comment REST API",
            description = "Update Comment REST API is used to update a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PutMapping("{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long postId,
                                                    @PathVariable Long commentId,
                                                    @RequestBody @Valid CommentDto commentDto){
        CommentDto comment = commentService.updateComment(postId, commentId, commentDto);
        return ResponseEntity.ok(comment);
    }

    @Operation(
            summary = "Delete Comment REST API",
            description = "Delete Comment REST API is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @DeleteMapping("{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment Deleted Successfully");
    }
}
