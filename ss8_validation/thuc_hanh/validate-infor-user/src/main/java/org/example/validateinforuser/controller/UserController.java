package org.example.validateinforuser.controller;

import org.example.validateinforuser.dto.UserRequestDto;
import org.example.validateinforuser.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @GetMapping("/user")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("userRequestDto", new UserRequestDto());
        return modelAndView;
    }

    @PostMapping("/validateUser")
    public ModelAndView checkValidation(@Validated @ModelAttribute UserRequestDto userRequestDto,
                                        BindingResult bindingResult) {
        new UserRequestDto().validate(userRequestDto,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/index");
        }
        User user = new User();

        BeanUtils.copyProperties(userRequestDto,user);
        return new ModelAndView("/result");
    }
}
