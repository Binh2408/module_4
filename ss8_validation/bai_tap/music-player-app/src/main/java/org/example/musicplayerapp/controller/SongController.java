package org.example.musicplayerapp.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import org.example.musicplayerapp.dto.SongRequestDto;
import org.example.musicplayerapp.model.Song;
import org.example.musicplayerapp.service.ISongService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("songs")
public class SongController {
    private final ISongService songService;

    @Autowired
    public SongController(ISongService songService) {
        this.songService = songService;
    }

    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "list";
    }

    @GetMapping("/create")
    public String showFormAdd(Model model) {
        model.addAttribute("songRequestDto",new SongRequestDto());
        return "create";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute SongRequestDto songRequestDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Song song = new Song();
        new SongRequestDto().validate(songRequestDto,bindingResult);
        if (bindingResult.hasErrors()) {
            return "create";
        }
        BeanUtils.copyProperties(songRequestDto,song);
        songService.save(song);
        redirectAttributes.addFlashAttribute("success","Add new song success");
        return "redirect:/songs";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Song song = songService.findById(id);
        SongRequestDto songRequestDto = new SongRequestDto();
        BeanUtils.copyProperties(song,songRequestDto);
        model.addAttribute("songRequestDto", songRequestDto);
        model.addAttribute("id",id);
        return "update";
    }

    @PostMapping("/{id}/update")
    public String update(@Validated @ModelAttribute SongRequestDto songRequestDto, BindingResult bindingResult,@PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
        new SongRequestDto().validate(songRequestDto,bindingResult);
        if(bindingResult.hasErrors()) {
            return "update";
        }

        Song song = new Song();
        BeanUtils.copyProperties(songRequestDto,song);
        song.setId(id);
        songService.save(song);
        redirectAttributes.addFlashAttribute("success","Updated song success");
        return "redirect:/songs";
    }
}
