package org.example.emailconfiguration.controller;

import org.example.emailconfiguration.model.Email;
import org.example.emailconfiguration.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/email")
public class EmailController {
    private IEmailService emailService;

    @Autowired
    public EmailController(IEmailService emailService) {
        this.emailService = emailService;
    }

    @ModelAttribute("languageOptions")
    public List<String> getAllLanguages() {
        return Arrays.asList("English", "Vietnamese", "Japanese", "Chinese");
    }

    @ModelAttribute("pageSizeOptions")
    public List<Integer> getAllPage() {
        return Arrays.asList(5, 10, 15, 25, 50, 100);
    }

    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("emails", emailService.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String showCreateForm(Model model) {
        if (!model.containsAttribute("email")) {
            model.addAttribute("email", new Email());
        }
        return "create";
    }

    @PostMapping("/add")
    public String submit(@ModelAttribute("email") Email email, RedirectAttributes redirectAttributes) {
        emailService.add(email);
        redirectAttributes.addFlashAttribute("emails", emailService.findAll());
        return "redirect:/email";
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showInformation(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Email email = emailService.findById(id);
        modelAndView.addObject("email",email);
        return modelAndView;
    }

    @PostMapping("/update")
    public String updateEmail(@ModelAttribute("email") Email email, RedirectAttributes redirectAttributes) {
        emailService.save(email); // cần hàm update theo id
        redirectAttributes.addFlashAttribute("emails", emailService.findAll());
        return "redirect:/email";
    }
}
