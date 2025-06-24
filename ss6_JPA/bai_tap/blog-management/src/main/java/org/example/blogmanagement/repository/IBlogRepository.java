package org.example.blogmanagement.repository;

import org.example.blogmanagement.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBlogRepository extends JpaRepository<Blog,Long> {
    @Query(value = "select * from blog where title like concat('%', :searchName,'%')",nativeQuery = true)
    List<Blog> searchByTitle(@Param("searchName") String searchName);
}
