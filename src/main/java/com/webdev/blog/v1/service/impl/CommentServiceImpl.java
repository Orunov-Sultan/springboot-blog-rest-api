package com.webdev.blog.v1.service.impl;

import com.webdev.blog.v1.dto.CommentDto;
import com.webdev.blog.v1.entity.Comment;
import com.webdev.blog.v1.entity.Post;
import com.webdev.blog.v1.exception.BlogApiExeption;
import com.webdev.blog.v1.exception.ResourceNotFoundException;
import com.webdev.blog.v1.repostitory.CommentRepository;
import com.webdev.blog.v1.repostitory.PostRepository;
import com.webdev.blog.v1.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final ModelMapper modelMapper;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);

        //Получаем пост по id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Пост с таким id: '" + postId + "' не найден")
        );

        //связываем пост и сущность Comment
        comment.setPost(post);

        //сохраняем коментарий в БД
        Comment savedComment = commentRepository.save(comment);

        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getCommentByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .toList();
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // Получаем пост
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Пост с таким id: '" + postId + "' не найден")
        );

        // Получаем коментарий
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Коментарий с таким id: '" + commentId + "' не найден")
        );

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiExeption("Коментарий не относится к посту");
        }
        return modelMapper.map(comment, CommentDto.class);
    }
}
