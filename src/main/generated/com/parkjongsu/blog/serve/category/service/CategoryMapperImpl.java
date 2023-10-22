package com.parkjongsu.blog.serve.category.service;

import com.parkjongsu.blog.serve.category.dto.CategoryDto;
import com.parkjongsu.blog.serve.category.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-22T10:38:00+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id( categoryDto.getId() );
        category.name( categoryDto.getName() );
        category.parent( categoryDto.getParent() );
        List<Category> list = categoryDto.getChild();
        if ( list != null ) {
            category.child( new ArrayList<Category>( list ) );
        }
        category.orders( categoryDto.getOrders() );
        category.url( categoryDto.getUrl() );

        return category.build();
    }

    @Override
    public CategoryDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.id( category.getId() );
        categoryDto.name( category.getName() );
        categoryDto.parent( category.getParent() );
        List<Category> list = category.getChild();
        if ( list != null ) {
            categoryDto.child( new ArrayList<Category>( list ) );
        }
        categoryDto.orders( category.getOrders() );
        categoryDto.url( category.getUrl() );

        return categoryDto.build();
    }
}
