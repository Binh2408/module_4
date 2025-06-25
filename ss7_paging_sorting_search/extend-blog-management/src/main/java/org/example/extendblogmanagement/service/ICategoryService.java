package org.example.extendblogmanagement.service;

import org.example.extendblogmanagement.model.Category;

import java.util.List;

public interface ICategoryService extends IGenerateService<Category> {
    List<Category> findAll();
}
