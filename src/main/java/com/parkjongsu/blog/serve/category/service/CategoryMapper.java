package com.parkjongsu.blog.serve.category.service;


import com.parkjongsu.blog.serve.category.dto.CategoryDto;
import com.parkjongsu.blog.serve.category.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper Instance = Mappers.getMapper(CategoryMapper.class);
    @Named("toEntity")
    Category toEntity(CategoryDto categoryDto);

    @Named("toDto")
    CategoryDto toDto(Category category);

}
