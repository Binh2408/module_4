package org.example.final_module_4.controller;

import org.example.final_module_4.dto.TransactionInfoDto;
import org.example.final_module_4.model.TransactionInfo;
import org.example.final_module_4.service.ICategoryService;
import org.example.final_module_4.service.ICustomerService;
import org.example.final_module_4.service.ITransactionInfoService;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/transactions")
public class TransactionInfoController {
    private final ICustomerService customerService;
    private final ICategoryService categoryService;
    private final ITransactionInfoService transactionInfoService;

    public TransactionInfoController(ICustomerService customerService, ICategoryService categoryService, ITransactionInfoService transactionInfoService) {
        this.customerService = customerService;
        this.categoryService = categoryService;
        this.transactionInfoService = transactionInfoService;
    }

    @GetMapping("")
    public String showList(@RequestParam(required = false, defaultValue = "0") int page,
                           @RequestParam(required = false, defaultValue = "20") int size,
                           Model model) {
        Pageable pageable = PageRequest.of(page, size);

        model.addAttribute("transactionInfo", transactionInfoService.findAll(pageable));
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "list";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        transactionInfoService.remove(id);
        redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/transactions";
    }

    @GetMapping("/create")
    public String showFormAdd(Model model) {
        model.addAttribute("transactionInfoDto", new TransactionInfoDto());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "create";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute TransactionInfoDto transactionInfoDto, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes, Model model) {
        TransactionInfo transactionInfo = new TransactionInfo();
        transactionInfoDto.validate(transactionInfoDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("transactionInfoDto",transactionInfoDto);
            model.addAttribute("customers", customerService.findAll());
            model.addAttribute("categories", categoryService.findAll());
            return "create";
        }
        BeanUtils.copyProperties(transactionInfoDto, transactionInfo);
        transactionInfoService.save(transactionInfo);
        redirectAttributes.addFlashAttribute("success", "Thêm mới thành côngn");
        return "redirect:/transactions";
    }
    @GetMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "0") int page,
                         @RequestParam(required = false, defaultValue = "2") int size,
                         @RequestParam(required = false) Long categoryId,
                         Model model) {
        Pageable pageable = PageRequest.of(page,size);
        Page<TransactionInfo> transactionInfos = transactionInfoService.search(categoryId,pageable);
        model.addAttribute("transactions",transactionInfos);
        model.addAttribute("categoryId",categoryId);
        return "list";
    }
}
