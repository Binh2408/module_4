package org.example.quanlyheo.controller;

import org.example.quanlyheo.model.Pig;
import org.example.quanlyheo.service.IOriginService;
import org.example.quanlyheo.service.IPigService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/pigs")
public class PigController {
    private IPigService pigService;
    private IOriginService originService;

    public PigController(IOriginService originService, IPigService pigService) {
        this.originService = originService;
        this.pigService = pigService;
    }

//    @GetMapping("")
//    public String showList(@RequestParam(required = false, defaultValue = "0") int page,
//                           @RequestParam(required = false,defaultValue = "5") int size,
//                           @RequestParam(required = false) String code,
//                           @RequestParam(required = false) Boolean status,
//                           @RequestParam(required = false) Long originId,
//                           Model model) {
//        Pageable pageable = PageRequest.of(page,size);
//        model.addAttribute("pigs", pigService.search(code, status, originId, pageable));
//        model.addAttribute("origins", originService.findAll());
//        model.addAttribute("originId",originId);
//        model.addAttribute("code",code);
//        model.addAttribute("status",status);
//        return "pig/list";
//    }
@GetMapping("")
public String showPigList(@RequestParam(required = false, defaultValue = "0") int page,
                          @RequestParam(required = false, defaultValue = "5") int size,
                          @RequestParam(required = false) String code,
                          @RequestParam(required = false) Boolean status,
                          @RequestParam(required = false) Long originId,
                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startInputDate,
                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endInputDate,
                          Model model) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Pig> pigs = pigService.search(code, status, originId, startInputDate, endInputDate, pageable);

    model.addAttribute("pigs", pigs);
    model.addAttribute("code", code);
    model.addAttribute("status", status);
    model.addAttribute("originId", originId);
    model.addAttribute("startInputDate", startInputDate);
    model.addAttribute("endInputDate", endInputDate);
    return "pig/list";
}

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        pigService.remove(id);
        redirectAttributes.addFlashAttribute("success","Xóa thành công");
        return "redirect:/pigs";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Pig pig = pigService.findById(id);
        model.addAttribute("pig",pig);
        model.addAttribute("origins", originService.findAll());
        return "/pig/edit";
    }

    @PostMapping("/update")
    public String update(Pig pig, RedirectAttributes redirectAttributes) {
        pigService.save(pig);
        redirectAttributes.addFlashAttribute("success","Sửa thành công");
        return "redirect:/pigs";
    }

    @GetMapping("/top-exported-pigs")
    public String showTopExportedPigs(@RequestParam(required = false, defaultValue = "0") int page,
                                      @RequestParam(required = false,defaultValue = "10") int size, Model model) {
        Pageable pageable = PageRequest.of(page,size);

        Page<Pig> topPigs = pigService.getTopExportedPigs(pageable);
        model.addAttribute("topPigs", topPigs);
        model.addAttribute("selectedLimit", size);
        return "pig/top_pigs"; // hoặc trang hiện tại nếu bạn gộp chung
    }

}
