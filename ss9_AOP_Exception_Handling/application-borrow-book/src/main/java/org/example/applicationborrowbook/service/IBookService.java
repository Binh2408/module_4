package org.example.applicationborrowbook.service;

import org.example.applicationborrowbook.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();
    Book findById(Long id);
    void save(Book book);

}
