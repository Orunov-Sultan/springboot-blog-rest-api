package com.webdev.blog.v1.service;

import com.webdev.blog.v1.dto.PostDto;
import com.webdev.blog.v1.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePost(Long id, PostDto postDto);
    void deletePost(Long id);

    List<PostDto> getPostsByCategoryId(Long categoryId);
}
