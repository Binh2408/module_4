package org.example.cart.controller;

import org.example.cart.model.Cart;
import org.example.cart.model.Product;
import org.example.cart.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("cart")

public class ProductController {
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }
    @GetMapping("/shop")
    public String showShop(Model model) {
        model.addAttribute("products", productService.findAll());
        return "list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        model.addAttribute("productDetail", product);
        return "detail";
    }

}
