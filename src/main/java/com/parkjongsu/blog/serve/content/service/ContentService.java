package com.parkjongsu.blog.serve.content.service;

import com.parkjongsu.blog.account.entity.Account;
import com.parkjongsu.blog.serve.content.dto.ContentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;

public interface ContentService {
    Page<ContentDto> getContentByPage(Pageable pageable);

    CollectionModel getPageResources(PagedResourcesAssembler<ContentDto> assembler, Page<ContentDto> page, Account account);
}
