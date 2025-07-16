package org.example.productmanagement.service.product;

import org.example.productmanagement.model.Product;
import org.example.productmanagement.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IProductService extends IGenerateService<Product> {
    Page<Product> searchProducts(@Param("keyword") String keyword,
                                 @Param("categoryId") Long categoryId,
                                 @Param("manufactureId") Long manufactureId,
                                 Pageable pageable);
}
