package com.parkjongsu.blog.serve.content.service;

import com.parkjongsu.blog.serve.content.dto.ReplyDto;
import com.parkjongsu.blog.serve.content.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface ReplyMapper {

    ReplyMapper Instance = Mappers.getMapper(ReplyMapper.class);
    @Named("toEntity")
    Reply toEntity(ReplyDto replyDto);

    @Named("toDto")
    ReplyDto toDto(Reply reply);


    @Mapping(target = "content", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "modifyDate", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "hit", ignore = true)
    @Mapping(target = "favorite", ignore = true)
    @Mapping(target = "child", ignore = true)
    ReplyDto toRecentDto(Reply reply);
}
