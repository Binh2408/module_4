package org.example.productmanagement.controller;

import org.example.productmanagement.dto.CategoryDto;
import org.example.productmanagement.model.Category;
import org.example.productmanagement.service.category.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "2") int size,
                           Model model) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Category> categoryPage = categoryService.findAll(pageable);
        model.addAttribute("categories",categoryPage);
        return "/category/list";
    }

    @GetMapping("/create")
    public String showFormAdd(Model model) {
        model.addAttribute("categoryDto",new CategoryDto());
        return "/category/create";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute CategoryDto categoryDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Category category = new Category();
        new CategoryDto().validate(categoryDto,bindingResult);
        if (bindingResult.hasErrors()) {
            return "/category/create";
        }
        BeanUtils.copyProperties(categoryDto,category);
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("success","Add success");
        return "redirect:/categories";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Category category = categoryService.findById(id);
        if (category != null) {
            categoryService.remove(id);
            redirectAttributes.addFlashAttribute("success","Delete success");
        } else {
            redirectAttributes.addFlashAttribute("error","Not found");
        }
        return "redirect:/categories";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category,categoryDto);
        model.addAttribute("categoryDto",categoryDto);
        model.addAttribute("id",id);
        return "/category/update";
    }

    @PostMapping("/{id}/update")
    public String update(@Validated @ModelAttribute CategoryDto categoryDto,BindingResult bindingResult,
                         @PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        new CategoryDto().validate(categoryDto,bindingResult);
        if (bindingResult.hasErrors()) {
            return "/category/update";
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto,category);
        category.setId(id);
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("success","Updated success");
        return "redirect:/categories";
    }

}
