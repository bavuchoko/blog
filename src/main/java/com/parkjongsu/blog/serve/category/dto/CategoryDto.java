package com.parkjongsu.blog.serve.category.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.parkjongsu.blog.serve.category.entity.Category;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer id;

    private String name;

    private Category parent;

    private List<Category> child;

    private int orders;

    private String url;
}
