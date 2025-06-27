package org.example.applicationborrowbook.service;

import org.example.applicationborrowbook.model.BorrowCode;
import org.example.applicationborrowbook.repository.IBorrowCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowCodeService implements IBorrowCodeService{
    private final IBorrowCodeRepository borrowCodeRepository;

    @Autowired
    public BorrowCodeService(IBorrowCodeRepository borrowCodeRepository) {
        this.borrowCodeRepository = borrowCodeRepository;
    }

    @Override
    public void save(BorrowCode borrowCode) {
        borrowCodeRepository.save(borrowCode);
    }

    @Override
    public Optional<BorrowCode> findByCode(String code) {
        return borrowCodeRepository.findByCode(code);
    }

    @Override
    public void deleteById(Long id) {
        borrowCodeRepository.deleteById(id);
    }
}
