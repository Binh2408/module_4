package org.example.blogajax.service;

import org.example.blogajax.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService extends IGenerateService<Blog>{
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> search(String searchName, Long categoryId ,Pageable pageable);
    List<Blog> searchBlogsByTitle(String keyword);

}
