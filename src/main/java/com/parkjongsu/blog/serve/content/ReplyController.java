package com.parkjongsu.blog.serve.content;


import com.parkjongsu.blog.serve.content.dto.ReplyDto;
import com.parkjongsu.blog.serve.content.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/reply",  produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping(value = "/recent")
    public ResponseEntity recent() {
        List<ReplyDto> list = replyService.getRecentCotent();
        CollectionModel resources = replyService.getPageResources(list);
        return ResponseEntity.ok().body(resources);
    }
}
