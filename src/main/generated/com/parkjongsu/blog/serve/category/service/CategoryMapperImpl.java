package com.parkjongsu.blog.serve.category.service;

import com.parkjongsu.blog.serve.category.dto.CategoryDto;
import com.parkjongsu.blog.serve.category.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-18T21:31:37+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryDto accountDto) {
        if ( accountDto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id( accountDto.getId() );
        category.name( accountDto.getName() );
        category.parent( accountDto.getParent() );
        List<Category> list = accountDto.getChild();
        if ( list != null ) {
            category.child( new ArrayList<Category>( list ) );
        }
        category.orders( accountDto.getOrders() );
        category.url( accountDto.getUrl() );

        return category.build();
    }

    @Override
    public CategoryDto toDto(Category account) {
        if ( account == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder categoryDto = CategoryDto.builder();

        return categoryDto.build();
    }
}
