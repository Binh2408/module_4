package org.example.blogwebservice.repository;

import org.example.blogwebservice.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlogRepository extends JpaRepository<Blog,Long> {
    Page<Blog> findBlogByTitleContaining(String searchTitle, Pageable pageable);
    Page<Blog> findBlogByTitleContainingAndCategory_Id(String searchTitle,Long categoryId,Pageable pageable);
    boolean existsByCategory_Id(Long categoryId);

}
