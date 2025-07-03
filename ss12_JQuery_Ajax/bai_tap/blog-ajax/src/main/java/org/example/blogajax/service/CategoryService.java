package org.example.blogajax.service;

import org.example.blogajax.model.Category;
import org.example.blogajax.repository.IBlogRepository;
import org.example.blogajax.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final IBlogRepository blogRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository, IBlogRepository blogRepository) {
        this.categoryRepository = categoryRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        if (blogRepository.existsByCategory_Id(id)) {
            throw new IllegalStateException("Cannot delete category because there are related blogs.");
        }
        categoryRepository.deleteById(id);
    }
}
