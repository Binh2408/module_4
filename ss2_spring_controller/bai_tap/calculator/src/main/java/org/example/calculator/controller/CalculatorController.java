package org.example.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String calculate(@RequestParam(required = false) Double num1, @RequestParam(required = false) Double num2, @RequestParam(required = false) String operator, Model model) {
        Double result = null;
        String operationName = "";

        if (num1 != null & num2 != null & operator != null) {
            switch (operator) {
                case "+":
                    result = num1+num2;
                    operationName="Addition";
                    break;
                case "-":
                    result = num1-num2;
                    operationName="Subtraction";
                    break;
                case "x":
                    result = num1*num2;
                    operationName = "Multiplication";
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                        operationName = "Division";
                    } else {
                        model.addAttribute("result", "Cannot divide by zero!");
                        return "calculator";
                    }
                    break;
            }
            model.addAttribute("result", result);
            model.addAttribute("operationName", operationName);
        }
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        return "calculator";
    }

}
