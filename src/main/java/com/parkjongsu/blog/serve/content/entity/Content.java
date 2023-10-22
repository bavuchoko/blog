package com.parkjongsu.blog.serve.content.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.parkjongsu.blog.account.entity.Account;
import com.parkjongsu.blog.serve.files.entity.Files;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    public Account createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyDate;

    public String title;


    @Column(columnDefinition = "TEXT")
    public String body;
    @Column(columnDefinition = "TEXT")
    public String bodyHtml;

    private String category;

    private int hit;
    private int favorite;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private List<Reply> replies;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Files> files;
    private String thumbnail;
}
