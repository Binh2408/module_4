package org.example.dictionary.controller;

import org.example.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {
    private final DictionaryService dictionaryService;
    @Autowired

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }
    @GetMapping("/convert")
    public String showForm(){
        return "dictionary";
    }
    @GetMapping("/converts")
    public String convert(
            @RequestParam("word") String word,
            @RequestParam("direction") String direction,
            Model model) {
        String result = dictionaryService.translate(word, direction);
        model.addAttribute("word", word);
        model.addAttribute("direction", direction);
        if (result == null || result.isEmpty()) {
            model.addAttribute("notFound", true);
        } else {
            model.addAttribute("result", result);
        }
        return "dictionary";
    }
}
