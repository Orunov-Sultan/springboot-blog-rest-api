package com.webdev.blog.v1.repostitory;

import com.webdev.blog.v1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryId(Long id);
}
