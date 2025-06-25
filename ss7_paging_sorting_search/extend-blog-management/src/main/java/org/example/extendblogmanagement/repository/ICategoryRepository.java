package org.example.extendblogmanagement.repository;

import org.example.extendblogmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
