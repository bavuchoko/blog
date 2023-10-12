package com.parkjongsu.blog.content.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.parkjongsu.blog.account.entity.Account;
import com.parkjongsu.blog.content.entity.Content;
import com.parkjongsu.blog.content.entity.Reply;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplayDto {
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
