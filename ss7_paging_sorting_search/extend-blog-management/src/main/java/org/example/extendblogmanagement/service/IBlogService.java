package org.example.extendblogmanagement.service;

import org.example.extendblogmanagement.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IGenerateService<Blog>{
    Page<Blog> findAll(Pageable pageable);
    Page<Blog> search(String searchName, Long categoryId ,Pageable pageable);
}
