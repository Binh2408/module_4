package org.example.productmanagement.controller.restapi;

import org.example.productmanagement.dto.ProductDto;
import org.example.productmanagement.model.Product;
import org.example.productmanagement.service.product.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
public class RestProductController {
    private final IProductService productService;

    @Autowired
    public RestProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long manufactureId,
            Pageable pageable) {
        Page<Product> productPage = productService.searchProducts(keyword,categoryId,manufactureId,pageable);
        if(productPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Validated @RequestBody ProductDto productDto, BindingResult bindingResult) {
        new ProductDto().validate(productDto,bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        productService.save(product);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
                                           @Validated @RequestBody ProductDto productDto,
                                           BindingResult result) {
        if (productService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }

        new ProductDto().validate(productDto, result);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setId(id);
        productService.save(product);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
