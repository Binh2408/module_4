package org.example.applicationborrowbook.repository;

import org.example.applicationborrowbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
}
