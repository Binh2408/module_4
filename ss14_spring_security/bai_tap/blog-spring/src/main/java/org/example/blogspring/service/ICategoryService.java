package org.example.blogspring.service;

import org.example.blogspring.model.Category;

import java.util.List;

public interface ICategoryService extends IGenerateService<Category> {
    List<Category> findAll();
}
