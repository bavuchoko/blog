package com.parkjongsu.blog.serve.content.service.impl;

import com.parkjongsu.blog.serve.content.ContentController;
import com.parkjongsu.blog.serve.content.ReplyController;
import com.parkjongsu.blog.serve.content.dto.ReplyDto;
import com.parkjongsu.blog.serve.content.entity.Reply;
import com.parkjongsu.blog.serve.content.repository.ReplyRepository;
import com.parkjongsu.blog.serve.content.service.ContentMapper;
import com.parkjongsu.blog.serve.content.service.ReplyMapper;
import com.parkjongsu.blog.serve.content.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public List<ReplyDto> getRecentCotent() {
        List<Reply> list = replyRepository.findRecentLimitedTo(6);
        return list.stream().map(ReplyMapper.Instance::toRecentDto).collect(Collectors.toList());
    }

    @Override
    public CollectionModel getPageResources(List<ReplyDto> list) {
        return CollectionModel.of(
                        list.stream().map(
                                e->(EntityModel.of(e)
                                        .add(linkTo(ReplyController.class)
                                                .slash(e.getId()).withSelfRel()))
                        ).collect(Collectors.toList()))
                .add(linkTo(ReplyController.class).slash("recent").withSelfRel());
    }


}

