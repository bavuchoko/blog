package com.parkjongsu.blog.serve.content.service;

import com.parkjongsu.blog.serve.content.dto.ReplyDto;
import com.parkjongsu.blog.serve.content.entity.Reply;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-20T23:04:55+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Amazon.com Inc.)"
)
@Component
public class ReplyMapperImpl implements ReplyMapper {

    @Override
    public Reply toEntity(ReplyDto replyDto) {
        if ( replyDto == null ) {
            return null;
        }

        Reply.ReplyBuilder reply = Reply.builder();

        reply.id( replyDto.getId() );
        reply.content( replyDto.getContent() );
        reply.createdBy( replyDto.getCreatedBy() );
        reply.createDate( replyDto.getCreateDate() );
        reply.modifyDate( replyDto.getModifyDate() );
        reply.body( replyDto.getBody() );
        reply.parent( replyDto.getParent() );
        reply.hit( replyDto.getHit() );
        reply.favorite( replyDto.getFavorite() );
        List<Reply> list = replyDto.getChild();
        if ( list != null ) {
            reply.child( new ArrayList<Reply>( list ) );
        }

        return reply.build();
    }

    @Override
    public ReplyDto toDto(Reply reply) {
        if ( reply == null ) {
            return null;
        }

        ReplyDto.ReplyDtoBuilder replyDto = ReplyDto.builder();

        replyDto.id( reply.getId() );
        replyDto.content( reply.getContent() );
        replyDto.createdBy( reply.getCreatedBy() );
        replyDto.createDate( reply.getCreateDate() );
        replyDto.modifyDate( reply.getModifyDate() );
        replyDto.body( reply.getBody() );
        replyDto.parent( reply.getParent() );
        replyDto.hit( reply.getHit() );
        replyDto.favorite( reply.getFavorite() );
        List<Reply> list = reply.getChild();
        if ( list != null ) {
            replyDto.child( new ArrayList<Reply>( list ) );
        }

        return replyDto.build();
    }

    @Override
    public ReplyDto toRecentDto(Reply reply) {
        if ( reply == null ) {
            return null;
        }

        ReplyDto.ReplyDtoBuilder replyDto = ReplyDto.builder();

        replyDto.id( reply.getId() );
        replyDto.body( reply.getBody() );

        return replyDto.build();
    }
}
