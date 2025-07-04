package org.example.productmanagement.controller;

import org.example.productmanagement.dto.ManufactureDto;
import org.example.productmanagement.model.Manufacture;
import org.example.productmanagement.service.manufacture.IManufactureService;
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
@RequestMapping("/manufactures")
public class ManufactureController {
    private final IManufactureService manufactureService;

    @Autowired
    public ManufactureController(IManufactureService manufactureService) {
        this.manufactureService = manufactureService;
    }

    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size,
                           Model model) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Manufacture> manufacturePage = manufactureService.findAll(pageable);
        model.addAttribute("manufacturePage",manufacturePage);
        return "manufacture/list";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Manufacture manufacture = manufactureService.findById(id);
        if (manufacture != null) {
            manufactureService.remove(id);
            redirectAttributes.addFlashAttribute("success","Delete success");
        } else {
            redirectAttributes.addFlashAttribute("error","Not found");
        }
        return "redirect:/manufactures";
    }
    @GetMapping("/create")
    public String showFormAdd(Model model) {
        model.addAttribute("manufactureDto", new ManufactureDto());
        return "manufacture/create";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute ManufactureDto manufactureDto,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        Manufacture manufacture = new Manufacture();
        new ManufactureDto().validate(manufactureDto,bindingResult);
        if (bindingResult.hasErrors()) {
            return "manufacture/create";
        }
        BeanUtils.copyProperties(manufactureDto,manufacture);
        manufactureService.save(manufacture);
        redirectAttributes.addFlashAttribute("success","Add success");
        return "redirect:/manufactures";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Manufacture manufacture = manufactureService.findById(id);
        ManufactureDto manufactureDto = new ManufactureDto();
        BeanUtils.copyProperties(manufacture,manufactureDto);
        model.addAttribute("manufactureDto",manufactureDto);
        model.addAttribute("id",id);
        return "manufacture/update";
    }

    @PostMapping("/{id}/update")
    public String update(@Validated @ModelAttribute ManufactureDto manufactureDto,
                         BindingResult bindingResult, @PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes){
        Manufacture manufacture = new Manufacture();
        new ManufactureDto().validate(manufactureDto,bindingResult);
        if (bindingResult.hasErrors()){
            return "manufacture/update";
        }
        BeanUtils.copyProperties(manufactureDto,manufacture);
        manufacture.setId(id);
        manufactureService.save(manufacture);
        redirectAttributes.addFlashAttribute("success","Updated success");
        return "redirect:/manufactures";
    }
}
