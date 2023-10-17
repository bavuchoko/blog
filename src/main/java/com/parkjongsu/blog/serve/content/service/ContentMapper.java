package com.parkjongsu.blog.serve.content.service;

import com.parkjongsu.blog.serve.content.dto.ContentDto;
import com.parkjongsu.blog.serve.content.entity.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    ContentMapper Instance = Mappers.getMapper(ContentMapper.class);
    @Named("toEntity")
    Content toEntity(ContentDto contentDto);

    @Named("toDto")
    ContentDto toDto(Content content);
}