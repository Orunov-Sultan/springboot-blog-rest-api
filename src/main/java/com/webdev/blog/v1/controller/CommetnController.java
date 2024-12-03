package com.webdev.blog.v1.controller;

import com.webdev.blog.v1.dto.CommentDto;
import com.webdev.blog.v1.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/posts")
@AllArgsConstructor
public class CommetnController {
    private CommentService commentService;

    @PostMapping("{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto){
        CommentDto comment = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}
