package org.example.blogajax.service;

import org.example.blogajax.model.Blog;
import org.example.blogajax.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService {
    private final IBlogRepository blogRepository;

    @Autowired
    public BlogService(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> search(String searchName, Long categoryId, Pageable pageable) {
        if (categoryId != null && categoryId != 0) {
            return blogRepository.findBlogByTitleContainingAndCategory_Id(searchName, categoryId, pageable);
        } else {
            return blogRepository.findBlogByTitleContaining(searchName, pageable);
        }
    }

    @Override
    public List<Blog> searchBlogsByTitle(String keyword) {
        return blogRepository.findBlogByTitleContainingIgnoreCase(keyword);
    }
}
