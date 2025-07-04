package org.example.productmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGenerateService<T> {
    List<T> findAll();
    Page<T> findAll(Pageable pageable);
    T findById(Long id);
    void save(T t);
    void remove(Long id);
}
