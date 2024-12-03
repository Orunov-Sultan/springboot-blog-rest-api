package com.webdev.blog.v1.service;

import com.webdev.blog.v1.dto.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);
}
