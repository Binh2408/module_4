package org.example.applicationborrowbook.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OutOfBookException.class)
    public String handleOutOfBook(OutOfBookException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "/exception/out-of-book";
    }

    @ExceptionHandler(InvalidBorrowCodeException.class)
    public String handleInvalidBorrowCode(InvalidBorrowCodeException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "/exception/invalid-borrow-code";
    }
}
