package org.example.productmanagementthymeleaf.repository;

import org.example.productmanagementthymeleaf.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();

    void save(Product product);

    Product findById(int id);

    void update(int id, Product product);

    void remove(int id);

    List<Product> findByName(String keyword);
}
