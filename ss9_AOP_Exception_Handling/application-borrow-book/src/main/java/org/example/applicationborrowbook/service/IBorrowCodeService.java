package org.example.applicationborrowbook.service;

import org.example.applicationborrowbook.model.BorrowCode;

import java.util.Optional;

public interface IBorrowCodeService {
    void save(BorrowCode borrowCode);
    Optional<BorrowCode> findByCode(String code);
    void deleteById(Long id);
}
