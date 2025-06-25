package org.example.extendblogmanagement.controller;

import org.example.extendblogmanagement.model.Blog;
import org.example.extendblogmanagement.service.IBlogService;
import org.example.extendblogmanagement.service.ICategoryService;
import org.example.extendblogmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    private final IBlogService blogService;
    private final IUserService userService;
    private final ICategoryService categoryService;

    @Autowired
    public BlogController(IBlogService blogService, IUserService userService, ICategoryService categoryService) {
        this.blogService = blogService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    //    @GetMapping("")
//    public String showList(@PageableDefault(page = 0,size = 3,sort = "title",direction = Sort.Direction.ASC) Pageable pageable, Model model) {
//        Page<Blog> blogPage= blogService.findAll(pageable);
//        model.addAttribute("blogs", blogPage);
//        return "list";
//    }
    @GetMapping("")
    public String search(@RequestParam(required = false, defaultValue = "0") int page,
                         @RequestParam(required = false, defaultValue = "3") int size,
                         @RequestParam(required = false, defaultValue = "") String searchName,
                         @RequestParam(required = false, defaultValue = "0") Long categoryId,
                         @RequestParam(required = false, defaultValue = "") String sortBy,
                         Model model) {
        Sort sort;
        // Xử lý sortBy
        switch (sortBy) {
            case "oldest":
                sort = Sort.by(Sort.Direction.ASC, "createdAt");
                break;
            default:
                sort = Sort.by(Sort.Direction.DESC, "createdAt");
                break;
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Blog> blogs = blogService.search(searchName, categoryId, pageable);
        model.addAttribute("searchName", searchName);
        model.addAttribute("blogs", blogs);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("sortBy", sortBy);
        return "list";
    }

    @GetMapping("/{id}/detail")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("blogDetail", blogService.findById(id));
        return "detail";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        blogService.remove(id);
        redirectAttributes.addFlashAttribute("success", "Delete success");
        return "redirect:/blogs";
    }

    @GetMapping("/create")
    public String showFormAdd(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "create";
    }

    @PostMapping("/save")
    public String save(Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("success", "Add success");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.findById(id));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "update";
    }

    @PostMapping("update")
    public String update(Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("success", "Updated success");
        return "redirect:/blogs";
    }

}
