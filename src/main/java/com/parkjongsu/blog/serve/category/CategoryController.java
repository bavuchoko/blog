package com.parkjongsu.blog.serve.category;


import com.parkjongsu.blog.serve.category.entity.Category;
import com.parkjongsu.blog.serve.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.parkjongsu.blog.common.WebCommon.badRequest;

@RestController
@RequestMapping(value = "/api/category",  produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity getCategories() {
        List<Category> list = categoryService.getCategoryTopList();
        CollectionModel resources = categoryService.getResources(list);
        return ResponseEntity.ok().body(resources);
    }


    @PostMapping
    public ResponseEntity createCategories(
            @RequestBody Category category,
            Errors errors
    ) {
        if(errors.hasErrors()){
            return badRequest(errors, this.getClass());
        }
        categoryService.save(category);

        List<Category> list = categoryService.getCategoryTopList();
        CollectionModel resources = categoryService.getResources(list);
        return ResponseEntity.ok().body(resources);
    }
}
