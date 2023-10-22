package com.parkjongsu.blog.serve.content.service;

import com.parkjongsu.blog.serve.content.dto.ContentDto;
import com.parkjongsu.blog.serve.content.entity.Content;
import com.parkjongsu.blog.serve.content.entity.Reply;
import com.parkjongsu.blog.serve.files.entity.Files;
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
        content.title( contentDto.getTitle() );
        content.body( contentDto.getBody() );
        content.bodyHtml( contentDto.getBodyHtml() );
        content.category( contentDto.getCategory() );
        content.hit( contentDto.getHit() );
        content.favorite( contentDto.getFavorite() );
        List<Reply> list = contentDto.getReplies();
        if ( list != null ) {
            content.replies( new ArrayList<Reply>( list ) );
        }
        List<Files> list1 = contentDto.getFiles();
        if ( list1 != null ) {
            content.files( new ArrayList<Files>( list1 ) );
        }
        content.thumbnail( contentDto.getThumbnail() );

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
        contentDto.title( content.getTitle() );
        contentDto.body( content.getBody() );
        contentDto.bodyHtml( content.getBodyHtml() );
        contentDto.category( content.getCategory() );
        contentDto.hit( content.getHit() );
        contentDto.favorite( content.getFavorite() );
        List<Reply> list = content.getReplies();
        if ( list != null ) {
            contentDto.replies( new ArrayList<Reply>( list ) );
        }
        List<Files> list1 = content.getFiles();
        if ( list1 != null ) {
            contentDto.files( new ArrayList<Files>( list1 ) );
        }
        contentDto.thumbnail( content.getThumbnail() );

        return contentDto.build();
    }

    @Override
    public ContentDto toDtoList(Content content) {
        if ( content == null ) {
            return null;
        }

        ContentDto.ContentDtoBuilder contentDto = ContentDto.builder();

        contentDto.id( content.getId() );
        contentDto.createdBy( content.getCreatedBy() );
        contentDto.createDate( content.getCreateDate() );
        contentDto.modifyDate( content.getModifyDate() );
        contentDto.title( content.getTitle() );
        contentDto.body( content.getBody() );
        contentDto.bodyHtml( content.getBodyHtml() );
        contentDto.category( content.getCategory() );
        contentDto.hit( content.getHit() );
        contentDto.favorite( content.getFavorite() );
        List<Reply> list = content.getReplies();
        if ( list != null ) {
            contentDto.replies( new ArrayList<Reply>( list ) );
        }
        contentDto.thumbnail( content.getThumbnail() );

        return contentDto.build();
    }

    @Override
    public ContentDto toRecentDto(Content content) {
        if ( content == null ) {
            return null;
        }

        ContentDto.ContentDtoBuilder contentDto = ContentDto.builder();

        contentDto.id( content.getId() );
        contentDto.body( content.getBody() );

        return contentDto.build();
    }
}
