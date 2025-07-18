package org.example.productmanagement.service.product;

import org.example.productmanagement.model.Product;
import org.example.productmanagement.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    private final IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> searchProducts(String keyword, Long categoryId, Long manufactureId, Pageable pageable) {
        String key = (keyword == null || keyword.isEmpty()) ? null : keyword;
        Long catId = (categoryId!= null && categoryId == 0) ? null : categoryId;
        Long manId = (manufactureId != null && manufactureId == 0) ? null : manufactureId;

        return productRepository.searchProducts(key,catId,manId,pageable);
    }
}
