package com.parkjongsu.blog.serve.content.dto;

import com.parkjongsu.blog.account.entity.Account;
import com.parkjongsu.blog.serve.content.entity.Reply;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentDto {

    public Long id;
    public Account createdBy;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @NotBlank
    public String subject;

    @NotBlank
    public String body;

    private String category;
    private int hit;
    private int favorite;
    private List<Reply> replies;
}
