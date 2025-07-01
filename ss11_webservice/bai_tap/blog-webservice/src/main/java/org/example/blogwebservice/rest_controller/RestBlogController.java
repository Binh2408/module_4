package org.example.blogwebservice.rest_controller;

import org.example.blogwebservice.dto.BlogDto;
import org.example.blogwebservice.model.Blog;
import org.example.blogwebservice.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/blogs")
public class RestBlogController {
    private final IBlogService blogService;

    @Autowired
    public RestBlogController(IBlogService blogService) {
        this.blogService = blogService;
    }

//    @GetMapping("")
//    public ResponseEntity<List<Blog>> findAll() {
//        List<Blog> blogList = blogService.findAll();
//        if(blogList.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(blogList,HttpStatus.OK);
//    }
    @GetMapping("")
    public ResponseEntity<BlogDto> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "") String searchName,
            @RequestParam(defaultValue = "0") Long categoryId,
            @RequestParam(defaultValue = "desc") String sortBy) {
        Sort sort = sortBy.equalsIgnoreCase("asc")
                ? Sort.by("createdAt").ascending()
                : Sort.by("createdAt").descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        // Sử dụng phương thức search như bên Controller thường
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

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findById(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if(blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blog,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Blog> update(@PathVariable Long id,@RequestBody Blog blog){
        Blog blogUpdate = blogService.findById(id);
        if (blogUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blog.setId(blogUpdate.getId());
        blogService.save(blog);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public ResponseEntity<Blog> save(@RequestBody Blog blog) {
        // kiem tra tính hợp lệ dữ liệu
        // nếu ko ok => thì xử lý thế nào => tự tìm hiểu
        // nếu ok => thêm mới
        blogService.save(blog);
        return new ResponseEntity<>(blog,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> delete(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if(blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        blogService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
