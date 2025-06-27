package org.example.applicationborrowbook.controller;

import org.example.applicationborrowbook.exception.InvalidBorrowCodeException;
import org.example.applicationborrowbook.exception.OutOfBookException;
import org.example.applicationborrowbook.model.Book;
import org.example.applicationborrowbook.model.BorrowCode;
import org.example.applicationborrowbook.service.IBookService;
import org.example.applicationborrowbook.service.IBorrowCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping("/books")
public class BookController {
    private final IBookService bookService;
    private final IBorrowCodeService borrowCodeService;

    @Autowired
    public BookController(IBookService bookService, IBorrowCodeService borrowCodeService) {
        this.bookService = bookService;
        this.borrowCodeService = borrowCodeService;
    }

    @GetMapping("")
    public String showList(Model model) {
        model.addAttribute("books",bookService.findAll());
        return "/book/list";
    }

    @GetMapping("/{id}/detail")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("bookDetail",bookService.findById(id));
        return "book/detail";
    }

    @GetMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id, Model model) throws OutOfBookException {
        Book book = bookService.findById(id);

        if (book.getQuantity() <= 0) {
            throw new OutOfBookException("Sách không còn để mượn");
        }

        book.setQuantity(book.getQuantity() - 1);
        bookService.save(book);

        String code = String.format("%05d",new Random().nextInt(100000));
        BorrowCode borrowCode = new BorrowCode();
        borrowCode.setCode(code);
        borrowCode.setCreateAt(LocalDate.now());
        borrowCode.setBook(book);
        borrowCodeService.save(borrowCode);

        model.addAttribute("code",code);
        return "/success/borrow-success";
    }

    @GetMapping("/return-form")
    public String showFormReturn() {
        return "return-form";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam String code, Model model) throws InvalidBorrowCodeException {
        Optional<BorrowCode> borrowCodeOpt = borrowCodeService.findByCode(code);
        if(borrowCodeOpt.isEmpty()) {
            throw new InvalidBorrowCodeException("Mã code không hợp lệ");
        }
        BorrowCode borrowCode = borrowCodeOpt.get();
        Book book = borrowCode.getBook();
        book.setQuantity(book.getQuantity()+1);
        bookService.save(book);

        borrowCodeService.deleteById(borrowCode.getId());
        model.addAttribute("bookName", book.getName());
        return "/success/return-success";
    }


    @GetMapping("/create")
    public String showFormAdd(Model model) {
        model.addAttribute("book",new Book());
        return "/book/create";
    }

    @PostMapping("/save")
    public String save(Book book, RedirectAttributes redirectAttributes) {
        bookService.save(book);
        redirectAttributes.addFlashAttribute("success","Add new book success");
        return "redirect:/books";
    }


}
