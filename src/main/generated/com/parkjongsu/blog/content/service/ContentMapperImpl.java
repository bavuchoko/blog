package com.parkjongsu.blog.content.service;

import com.parkjongsu.blog.content.dto.ContentDto;
import com.parkjongsu.blog.content.entity.Content;
import com.parkjongsu.blog.content.entity.Reply;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-14T23:26:41+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
)
@Component
public class ContentMapperImpl implements ContentMapper {

    @Override
    public Content toEntity(ContentDto contentDto) {
        if ( contentDto == null ) {
            return null;
        }

        Content.ContentBuilder content = Content.builder();

        content.id( contentDto.getId() );
        content.createdBy( contentDto.getCreatedBy() );
        content.createDate( contentDto.getCreateDate() );
        content.modifyDate( contentDto.getModifyDate() );
        content.subject( contentDto.getSubject() );
        content.body( contentDto.getBody() );
        content.category( contentDto.getCategory() );
        content.hit( contentDto.getHit() );
        content.favorite( contentDto.getFavorite() );
        List<Reply> list = contentDto.getReplies();
        if ( list != null ) {
            content.replies( new ArrayList<Reply>( list ) );
        }

        return content.build();
    }

    @Override
    public ContentDto toDto(Content content) {
        if ( content == null ) {
            return null;
        }

        ContentDto.ContentDtoBuilder contentDto = ContentDto.builder();

        contentDto.id( content.getId() );
        contentDto.createdBy( content.getCreatedBy() );
        contentDto.createDate( content.getCreateDate() );
        contentDto.modifyDate( content.getModifyDate() );
        contentDto.subject( content.getSubject() );
        contentDto.body( content.getBody() );
        contentDto.category( content.getCategory() );
        contentDto.hit( content.getHit() );
        contentDto.favorite( content.getFavorite() );
        List<Reply> list = content.getReplies();
        if ( list != null ) {
            contentDto.replies( new ArrayList<Reply>( list ) );
        }

        return contentDto.build();
    }
}
