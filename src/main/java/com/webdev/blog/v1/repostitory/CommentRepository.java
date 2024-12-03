package com.webdev.blog.v1.repostitory;

import com.webdev.blog.v1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
