package com.parkjongsu.blog.serve.content.dto;

import com.parkjongsu.blog.account.entity.Account;
import com.parkjongsu.blog.serve.content.entity.Content;
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
public class ReplyDto {
    private Long id;
    private Content content;
    private Account createdBy;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @NotBlank
    private String body;
    private Reply parent;
    private int hit;
    private int favorite;
    private List<Reply> child;
}
