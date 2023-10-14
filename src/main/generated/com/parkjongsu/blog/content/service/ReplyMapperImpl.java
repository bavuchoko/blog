package com.parkjongsu.blog.content.service;

import com.parkjongsu.blog.content.dto.ReplayDto;
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
public class ReplyMapperImpl implements ReplyMapper {

    @Override
    public Reply toEntity(ReplayDto replayDto) {
        if ( replayDto == null ) {
            return null;
        }

        Reply.ReplyBuilder reply = Reply.builder();

        reply.id( replayDto.getId() );
        reply.content( replayDto.getContent() );
        reply.createdBy( replayDto.getCreatedBy() );
        reply.createDate( replayDto.getCreateDate() );
        reply.modifyDate( replayDto.getModifyDate() );
        reply.body( replayDto.getBody() );
        reply.parent( replayDto.getParent() );
        reply.hit( replayDto.getHit() );
        reply.favorite( replayDto.getFavorite() );
        List<Reply> list = replayDto.getChild();
        if ( list != null ) {
            reply.child( new ArrayList<Reply>( list ) );
        }

        return reply.build();
    }

    @Override
    public ReplayDto toDto(Reply reply) {
        if ( reply == null ) {
            return null;
        }

        ReplayDto.ReplayDtoBuilder replayDto = ReplayDto.builder();

        replayDto.id( reply.getId() );
        replayDto.content( reply.getContent() );
        replayDto.createdBy( reply.getCreatedBy() );
        replayDto.createDate( reply.getCreateDate() );
        replayDto.modifyDate( reply.getModifyDate() );
        replayDto.body( reply.getBody() );
        replayDto.parent( reply.getParent() );
        replayDto.hit( reply.getHit() );
        replayDto.favorite( reply.getFavorite() );
        List<Reply> list = reply.getChild();
        if ( list != null ) {
            replayDto.child( new ArrayList<Reply>( list ) );
        }

        return replayDto.build();
    }
}
