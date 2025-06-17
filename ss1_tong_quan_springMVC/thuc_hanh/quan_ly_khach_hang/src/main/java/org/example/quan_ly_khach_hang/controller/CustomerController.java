package org.example.quan_ly_khach_hang.controller;

import org.example.quan_ly_khach_hang.model.Customer;
import org.example.quan_ly_khach_hang.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String showList(Model model) {
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers",customerList);
        return "list";
    }
    @GetMapping("/detail")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "detail";
    }
}