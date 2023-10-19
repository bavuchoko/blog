package com.parkjongsu.blog.serve.content;

import com.parkjongsu.blog.account.entity.Account;
import com.parkjongsu.blog.common.annotation.CurrentUser;
import com.parkjongsu.blog.serve.content.dto.ContentDto;
import com.parkjongsu.blog.serve.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/content",  produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping
    public ResponseEntity getContentList(
            @CurrentUser Account account,
            @RequestParam(name = "category", required = false) String category,
            Pageable pageable,
            PagedResourcesAssembler<ContentDto> assembler
            ){

        Page<ContentDto> page = contentService.getContentByPage(pageable);
        CollectionModel pageResources = contentService.getPageResources(assembler, page,account);

        return ResponseEntity.ok().body(pageResources);
    }


}
