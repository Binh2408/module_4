package org.example.productmanagementthymeleaf.repository;

import org.example.productmanagementthymeleaf.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {
    private static final List<Product> productList;
    private static int currentId = 1;

    static {
        productList = new ArrayList<>();
        productList.add(new Product(currentId++, "Máy tính DELL", 20000, "15inch, màn hình cong","DELL"));
        productList.add(new Product(currentId++, "Máy tính APPLE", 20000,"20inch, màn hình mỏng" ,"APPLE"));
        productList.add(new Product(currentId++, "Máy tính ASUS", 20000, "25inch, màn hình led" ,"DELL"));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        product.setId(currentId++);
        productList.add(product);
    }

    @Override
    public Product findById(int id) {
        for (Product product: productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Product product) {
        Product existing = findById(id);
        if (existing != null) {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            existing.setDescription(product.getDescription());
            existing.setManufacture(product.getManufacture());
        }
    }

    @Override
    public List<Product> findByName(String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id){
                productList.remove(i);
                return;
            }
        }
    }
}
