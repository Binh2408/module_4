package org.example.blogajax.service;

import org.example.blogajax.model.Category;

import java.util.List;

public interface ICategoryService extends IGenerateService<Category> {
    List<Category> findAll();
}
