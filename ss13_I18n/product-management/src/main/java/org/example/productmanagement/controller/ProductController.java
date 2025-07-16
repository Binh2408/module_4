package org.example.productmanagement.controller;

import org.example.productmanagement.dto.ProductDto;
import org.example.productmanagement.model.Product;
import org.example.productmanagement.service.category.ICategoryService;
import org.example.productmanagement.service.manufacture.IManufactureService;
import org.example.productmanagement.service.product.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final IProductService productService;
    private final ICategoryService categoryService;
    private final IManufactureService manufactureService;

    public ProductController(IProductService productService, ICategoryService categoryService, IManufactureService manufactureService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufactureService = manufactureService;
    }

//    @GetMapping("")
//    public String showList(@RequestParam(defaultValue = "0") int page,
//                           @RequestParam(defaultValue = "5") int size,
//                           Model model) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Product> productPage = productService.findAll(pageable);
//        model.addAttribute("productPage", productPage);
//        return "/product/list";
//    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (product != null) {
            productService.remove(id);
            redirectAttributes.addFlashAttribute("success", "Delete success");
        } else {
            redirectAttributes.addFlashAttribute("error", "Not found");
        }
        return "redirect:/products";
    }

    @GetMapping("/create")
    public String showFormAdd(Model model) {
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("manufactureList", manufactureService.findAll());
        return "/product/create";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute ProductDto productDto,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        Product product = new Product();
        new ProductDto().validate(productDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/product/create";
        }
        BeanUtils.copyProperties(productDto, product);
        productService.save(product);
        redirectAttributes.addFlashAttribute("success", "Add success");
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        model.addAttribute("productDto", productDto);
        model.addAttribute("id", id);
        model.addAttribute("categoryList", categoryService.findAll());
        model.addAttribute("manufactureList", manufactureService.findAll());
        return "/product/update";
    }

    @PostMapping("/{id}/update")
    public String update(@Validated @ModelAttribute ProductDto productDto,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes) {
        new ProductDto().validate(productDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/product/update";
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setId(id);
        productService.save(product);
        redirectAttributes.addFlashAttribute("success", "Updated success");
        return "redirect:/products";
    }

    @GetMapping("")
    public String showProductList(@RequestParam(required = false) String keyword,
                                  @RequestParam(required = false) Long categoryId,
                                  @RequestParam(required = false) Long manufactureId,
                                  @PageableDefault(size = 5) Pageable pageable,
                                  Model model) {

        Page<Product> productPage = productService.searchProducts(keyword, categoryId, manufactureId, pageable);

        model.addAttribute("productPage", productPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("manufactureId", manufactureId);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("manufacturers", manufactureService.findAll());

        return "product/list";
    }


}
