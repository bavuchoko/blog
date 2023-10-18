package com.parkjongsu.blog.serve.category.repository;

import com.parkjongsu.blog.serve.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<Category, Integer> {
    List findAllByParent(Category category);
}
