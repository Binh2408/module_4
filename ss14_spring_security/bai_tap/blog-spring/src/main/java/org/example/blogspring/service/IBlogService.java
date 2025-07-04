package org.example.blogspring.service;

import org.example.blogspring.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGenerateService<Blog>{
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> search(String searchName, Long categoryId ,Pageable pageable);
}
