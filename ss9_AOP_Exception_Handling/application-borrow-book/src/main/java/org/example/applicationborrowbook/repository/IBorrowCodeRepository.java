package org.example.applicationborrowbook.repository;

import org.example.applicationborrowbook.model.BorrowCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBorrowCodeRepository extends JpaRepository<BorrowCode,Long> {
    Optional<BorrowCode> findByCode(String code);
}
