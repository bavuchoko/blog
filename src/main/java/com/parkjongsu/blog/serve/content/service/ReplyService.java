package com.parkjongsu.blog.serve.content.service;

import com.parkjongsu.blog.serve.content.dto.ReplyDto;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public interface ReplyService {
    List<ReplyDto> getRecentCotent();

    CollectionModel getPageResources(List<ReplyDto> list);
}
