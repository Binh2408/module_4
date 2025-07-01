package org.example.blogwebservice.rest_controller;

import org.example.blogwebservice.dto.BlogDto;
import org.example.blogwebservice.model.Blog;
import org.example.blogwebservice.model.Category;
import org.example.blogwebservice.service.IBlogService;
import org.example.blogwebservice.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class RestCategoryController {
    private final ICategoryService categoryService;
    private final IBlogService blogService;

    @Autowired
    public RestCategoryController(ICategoryService categoryService, IBlogService blogService) {
        this.categoryService = categoryService;
        this.blogService = blogService;
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categoryList = categoryService.findAll();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {
        Category categoryDelete = categoryService.findById(id);
        if (categoryDelete == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category categoryUpdate = categoryService.findById(id);
        if (categoryUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setId(categoryUpdate.getId());
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{categoryId}/blogs")
    public ResponseEntity<BlogDto> getBlogsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "") String searchName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "desc") String sortBy
    ) {
        Sort sort = sortBy.equalsIgnoreCase("asc")
                ? Sort.by("createdAt").ascending()
                : Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Blog> blogPage = blogService.search(searchName, categoryId, pageable);

        if (blogPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        BlogDto response = new BlogDto(
                blogPage.getContent(),
                blogPage.getNumber(),
                blogPage.getTotalPages(),
                blogPage.getTotalElements()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
