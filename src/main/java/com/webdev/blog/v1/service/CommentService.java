package com.webdev.blog.v1.service;

import com.webdev.blog.v1.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getCommentByPostId(Long postId);

    CommentDto getCommentById(Long postId, Long commentId);
}
