package org.example.blogmanagement.service;

import org.example.blogmanagement.model.Blog;

import java.util.List;

public interface IBlogService extends IGenerateService<Blog> {
    List<Blog> findByTitle(String searchName);
}
