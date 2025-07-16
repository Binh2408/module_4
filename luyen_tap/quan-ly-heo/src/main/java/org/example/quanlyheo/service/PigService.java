package org.example.quanlyheo.service;

import org.example.quanlyheo.model.Pig;
import org.example.quanlyheo.repository.IPigRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PigService implements IPigService {
    private IPigRepository pigRepository;

    public PigService(IPigRepository pigRepository) {
        this.pigRepository = pigRepository;
    }

    @Override
    public Page<Pig> findAll(Pageable pageable) {
        return pigRepository.findAll(pageable);
    }

    @Override
    public void remove(Long id) {
        pigRepository.deleteById(id);
    }

    @Override
    public Pig findById(Long id) {
        return pigRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Pig pig) {
        if (pig.getOutputDate() == null) {
            pig.setOutputWeight(pig.getInputWeight());
        }
        pigRepository.save(pig);
    }

    @Override
    public Page<Pig> search(String code, Boolean status, Long originId, LocalDate startInputDate, LocalDate endInputDate, Pageable pageable) {
        return pigRepository.search(code,status,originId,startInputDate,endInputDate,pageable);
    }

//    @Override
//    public Page<Pig> search(String code, Boolean status, Long originId, Pageable pageable) {
//        return pigRepository.search(code,status,originId,pageable);
//    }

    @Override
    public Page<Pig> getTopExportedPigs(Pageable pageable) {
        return pigRepository.findTopExportedPigs(pageable);
    }
}
