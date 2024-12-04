package com.webdev.blog.v1.service.impl;

import com.webdev.blog.v1.dto.CommentDto;
import com.webdev.blog.v1.entity.Comment;
import com.webdev.blog.v1.entity.Post;
import com.webdev.blog.v1.exception.BlogApiException;
import com.webdev.blog.v1.exception.ResourceNotFoundException;
import com.webdev.blog.v1.repostitory.CommentRepository;
import com.webdev.blog.v1.repostitory.PostRepository;
import com.webdev.blog.v1.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Post post = getPostById(postId);

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
        Post post = getPostById(postId);

        // Получаем коментарий
        Comment comment = getCommentById(commentId);

        // Проверяем относится ли комент к посту
        checkingCommentsBelongToPost(comment, post);

        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        // Получаем пост
        Post post = getPostById(postId);

        // Получаем коментарий
        Comment comment = getCommentById(commentId);

        // Проверяем относится ли комент к посту
        checkingCommentsBelongToPost(comment, post);

        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());

        Comment updatedComment = commentRepository.save(comment);

        return modelMapper.map(updatedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // Получаем пост
        Post post = getPostById(postId);

        // Получаем коментарий
        Comment comment = getCommentById(commentId);

        // Проверяем относится ли комент к посту
        checkingCommentsBelongToPost(comment, post);

        commentRepository.delete(comment);
    }


    private Post getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Пост с таким id: '" + postId + "' не найден")
        );
        return post;
    }

    private Comment getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Коментарий с таким id: '" + commentId + "' не найден")
        );
        return comment;
    }

    private void checkingCommentsBelongToPost(Comment comment, Post post){
        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException("Коментарий не относится к посту");
        }
    }
}