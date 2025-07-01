package org.example.blogwebservice.service;

import org.example.blogwebservice.model.Category;

import java.util.List;

public interface ICategoryService extends IGenerateService<Category> {
    List<Category> findAll();
}
