package com.parkjongsu.blog.serve.content.service.impl;


import com.parkjongsu.blog.account.AccountController;
import com.parkjongsu.blog.account.entity.Account;
import com.parkjongsu.blog.serve.content.ContentController;
import com.parkjongsu.blog.serve.content.dto.ContentDto;
import com.parkjongsu.blog.serve.content.entity.Content;
import com.parkjongsu.blog.serve.content.repository.ContentJpaRepository;
import com.parkjongsu.blog.serve.content.repository.ContentRepository;
import com.parkjongsu.blog.serve.content.service.ContentMapper;
import com.parkjongsu.blog.serve.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentJpaRepository contentJpaRepository;

    private final ContentRepository contentRepository;
    @Override
    public Page<ContentDto> getContentByPage(Pageable pageable){
        Page<Content> contentPage = contentJpaRepository.findAll(pageable);
        Page<ContentDto> contentDtoPage = contentPage.map(ContentMapper.Instance::toDtoList);
        return contentDtoPage;
    }

    @Override
    public CollectionModel getPageResources(
            PagedResourcesAssembler<ContentDto> assembler,
            Page<ContentDto> page,
            Account account){
        return assembler.toModel(page, e -> {
            EntityModel<ContentDto> model = EntityModel.of(e)
                    .add(linkTo(AccountController.class).slash(e.getId()).withSelfRel());
            if (account != null && account.equals(e.getCreatedBy())) {
                model.add(linkTo(AccountController.class).slash(e.getId()).withRel("update"));
            }
            return model;
        });
    }


    @Override
    public List<ContentDto> getRecentCotent() {
        List<Content> list = contentRepository.findRecentLimitedTo(6);
        return list.stream().map(ContentMapper.Instance::toRecentDto).collect(Collectors.toList());
    }
    @Override
    public CollectionModel getPageResources(List<ContentDto> list){
        return CollectionModel.of(
                list.stream().map(
                        e->(EntityModel.of(e)
                                .add(linkTo(ContentController.class)
                                        .slash(e.getId()).withSelfRel()))
                ).collect(Collectors.toList()))
                .add(linkTo(ContentController.class).slash("recent").withSelfRel());
    }

}
