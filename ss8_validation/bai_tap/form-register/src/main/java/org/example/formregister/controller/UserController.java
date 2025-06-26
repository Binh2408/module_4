package org.example.formregister.controller;

import jakarta.validation.Valid;
import org.example.formregister.dto.UserRequestDto;
import org.example.formregister.model.User;
import org.example.formregister.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String showFormRegister(Model model) {
        model.addAttribute("userRequestDto", new UserRequestDto());
        return "index";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute UserRequestDto userRequestDto, BindingResult bindingResult, Model model) {
        User user = new User();
        new UserRequestDto().validate(userRequestDto,bindingResult);
        if (bindingResult.hasErrors()) {
            return "index";
        }

        BeanUtils.copyProperties(userRequestDto,user);
        userService.save(user);
        return "result";


    }


}
