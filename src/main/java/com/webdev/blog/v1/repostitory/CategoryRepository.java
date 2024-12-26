package com.webdev.blog.v1.repostitory;

import com.webdev.blog.v1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
