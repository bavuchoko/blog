package com.parkjongsu.blog.serve.content.service;

import com.parkjongsu.blog.serve.content.dto.ReplayDto;
import com.parkjongsu.blog.serve.content.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface ReplyMapper {

    ReplyMapper Instance = Mappers.getMapper(ReplyMapper.class);
    @Named("toEntity")
    Reply toEntity(ReplayDto replayDto);

    @Named("toDto")
    ReplayDto toDto(Reply reply);
}
