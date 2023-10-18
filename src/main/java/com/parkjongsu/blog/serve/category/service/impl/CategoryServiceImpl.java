package com.parkjongsu.blog.serve.category.service.impl;

import com.parkjongsu.blog.serve.category.CategoryController;
import com.parkjongsu.blog.serve.category.dto.CategoryDto;
import com.parkjongsu.blog.serve.category.entity.Category;
import com.parkjongsu.blog.serve.category.repository.CategoryJpaRepository;
import com.parkjongsu.blog.serve.category.service.CategoryMapper;
import com.parkjongsu.blog.serve.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public List getCategoryList() {
        return categoryJpaRepository.findAll();
    }

    @Override
    public List getCategoryTopList() {
        return categoryJpaRepository.findAllByParent(null);
    }

    @Override
    public void save(Category category) {
        categoryJpaRepository.save(category);
    }

    @Override
    public CollectionModel getResources(List<Category> list) {
        List<CategoryDto> dtoList = list.stream().map(
                category -> CategoryMapper.Instance.toDto(category)
        ).collect(Collectors.toList());
        return CollectionModel.of(dtoList).add(linkTo(CategoryController.class).withSelfRel());
    }

}
