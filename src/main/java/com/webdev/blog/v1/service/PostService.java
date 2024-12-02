package com.webdev.blog.v1.service;

import com.webdev.blog.v1.dto.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
