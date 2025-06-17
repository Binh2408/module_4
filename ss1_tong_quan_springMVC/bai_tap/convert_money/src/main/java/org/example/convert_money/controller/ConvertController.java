package org.example.convert_money.controller;

import org.example.convert_money.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConvertController {
    private final ConvertService convertService;

    @Autowired
    public ConvertController(ConvertService convertService) {
        this.convertService = convertService;
    }


    @GetMapping("/convert")
    public String showForm() {
        return "convert";
    }
    @GetMapping("/converts")
    public String convert(@RequestParam("amount") double amount,@RequestParam("direction") String direction,Model model) {
        double result = 0;
        if ("usdToVnd".equals(direction)) {
            result = convertService.convertUsdToVnd(amount);
        } else {
            result = convertService.convertVndToUsd(amount);
        }
        model.addAttribute("amount", amount);
        model.addAttribute("direction", direction);
        model.addAttribute("result", result);

        return "convert";
    }
}
