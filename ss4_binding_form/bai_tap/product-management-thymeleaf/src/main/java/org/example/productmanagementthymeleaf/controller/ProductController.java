package org.example.productmanagementthymeleaf.controller;

import org.example.productmanagementthymeleaf.model.Product;
import org.example.productmanagementthymeleaf.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String showList(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("products", productList);
        return "/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "/create";
    }

    @PostMapping("/save")
    public String save(Product product, RedirectAttributes redirect) {
        productService.save(product);
        redirect.addFlashAttribute("success","Created product successfully");
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("product",productService.findById(id));
        return"/update";
    }

    @PostMapping("/update")
    public String update(Product product, RedirectAttributes redirect) {
        productService.update(product.getId(),product);
        redirect.addFlashAttribute("success", "Updated product successfully!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/detail")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("product",productService.findById(id));
        return "detail";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        productService.remove(id);
        redirect.addFlashAttribute("success", "Deleted product successfully!");
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Product> productList;
        if (keyword != null && !keyword.isEmpty()){
            productList = productService.findByName(keyword);
        } else {
            productList = productService.findAll();
        }
        model.addAttribute("products", productList);
        model.addAttribute("keyword",keyword);
        return "list";
    }
}
