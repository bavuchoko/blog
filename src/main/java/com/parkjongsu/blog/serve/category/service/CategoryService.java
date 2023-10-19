package com.parkjongsu.blog.serve.category.service;

import com.parkjongsu.blog.serve.category.dto.CategoryDto;
import com.parkjongsu.blog.serve.category.entity.Category;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public interface CategoryService {

    List getCategoryList();
    CollectionModel getResources(List<Category> list);
    List getCategoryTopList();

    void save(CategoryDto categoryDto);

    List findRecentContent();
}
