package org.example.quanlyheo.service;

import org.example.quanlyheo.model.Pig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IPigService {
    Page<Pig> findAll(Pageable pageable);
    void remove(Long id);
    Pig findById(Long id);
    void save(Pig pig);
//    Page<Pig> search(String code, Boolean status, Long originId, Pageable pageable);
Page<Pig> search(String code, Boolean status, Long originId, LocalDate startInputDate, LocalDate endInputDate, Pageable pageable);

    Page<Pig> getTopExportedPigs(Pageable pageable);
}
