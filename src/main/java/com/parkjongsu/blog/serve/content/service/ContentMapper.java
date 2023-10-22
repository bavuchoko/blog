package com.parkjongsu.blog.serve.content.service;

import com.parkjongsu.blog.serve.content.dto.ContentDto;
import com.parkjongsu.blog.serve.content.entity.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    ContentMapper Instance = Mappers.getMapper(ContentMapper.class);
    @Named("toEntity")
    Content toEntity(ContentDto contentDto);

    @Named("toDto")
    ContentDto toDto(Content content);

    @Named("toDto")
    @Mapping(target = "files", ignore = true)
    ContentDto toDtoList(Content content);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "bodyHtml", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "hit", ignore = true)
    @Mapping(target = "favorite", ignore = true)
    @Mapping(target = "replies", ignore = true)
    @Mapping(target = "files", ignore = true)
    @Mapping(target = "thumbnail", ignore = true)
    ContentDto toRecentDto(Content content);
}
