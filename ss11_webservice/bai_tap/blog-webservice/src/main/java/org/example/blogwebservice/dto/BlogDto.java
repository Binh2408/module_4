package org.example.blogwebservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.blogwebservice.model.Blog;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private List<Blog> blogs;
    private int currentPage;
    private int totalPages;
    private Long totalElements;
}
