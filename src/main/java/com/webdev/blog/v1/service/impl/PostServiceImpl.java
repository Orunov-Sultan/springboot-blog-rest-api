package com.webdev.blog.v1.service.impl;

import com.webdev.blog.v1.dto.PostDto;
import com.webdev.blog.v1.dto.PostResponse;
import com.webdev.blog.v1.entity.Category;
import com.webdev.blog.v1.entity.Post;
import com.webdev.blog.v1.exception.ResourceNotFoundException;
import com.webdev.blog.v1.repostitory.CategoryRepository;
import com.webdev.blog.v1.repostitory.PostRepository;
import com.webdev.blog.v1.service.CategoryService;
import com.webdev.blog.v1.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("The category you specified was not found")
        );
        Post post = modelMapper.map(postDto, Post.class);
        post.setCategory(category);
        Post savedPost = postRepository.save(post);

        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("the post with the id: '" + id + "' was not found")
        );
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("the post with the id: '" + id + "' was not found")
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);

        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("the post with the id: '" + id + "' was not found")
        );
        postRepository.delete(post);
    }
}
