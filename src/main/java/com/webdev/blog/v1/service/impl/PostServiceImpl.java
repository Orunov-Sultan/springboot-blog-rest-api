package com.webdev.blog.v1.service.impl;

import com.webdev.blog.v1.dto.PostDto;
import com.webdev.blog.v1.entity.Post;
import com.webdev.blog.v1.repostitory.PostRepository;
import com.webdev.blog.v1.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        Post savedPost = postRepository.save(post);

        return modelMapper.map(savedPost, PostDto.class);
    }
}
