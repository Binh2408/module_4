package org.example.blogwebservice.repository;

import org.example.blogwebservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
