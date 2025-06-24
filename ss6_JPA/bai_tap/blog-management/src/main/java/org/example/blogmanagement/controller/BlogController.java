package org.example.blogmanagement.controller;

import lombok.Getter;
import org.example.blogmanagement.model.Blog;
import org.example.blogmanagement.service.IBlogService;
import org.example.blogmanagement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    private final IBlogService blogService;
    private final IUserService userService;

    @Autowired
    public BlogController(IBlogService blogService, IUserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("blog",blogService.findAll());
        return "list";
    }

    @GetMapping("/{id}/detail")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("blog",blogService.findById(id));
        return "detail";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        blogService.remove(id);
        redirectAttributes.addFlashAttribute("success", "Delete success");
        return "redirect:/blogs";
    }

    @GetMapping("/create")
    public String showAddForm(Model model) {
        model.addAttribute("blog",new Blog());
        model.addAttribute("users",userService.findAll());
        return "create";
    }

    @PostMapping("/save")
    public String save(Blog blog, RedirectAttributes redirectAttributes){
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("success","Add success");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("blog",blogService.findById(id));
        model.addAttribute("users",userService.findAll());
        return "update";
    }

    @PostMapping("/update")
    public String update(Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("success","Updated success");
        return "redirect:/blogs";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "searchName",required = false) String searchName,Model model) {
        List<Blog> blogList;
        if(searchName != null) {
            blogList = blogService.findByTitle(searchName);
        } else {
            blogList = blogService.findAll();
        }
        model.addAttribute("blog",blogList);
        model.addAttribute("searchName",searchName);
        return "list";
    }
}
