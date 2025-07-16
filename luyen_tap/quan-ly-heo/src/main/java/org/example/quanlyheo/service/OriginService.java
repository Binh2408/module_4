package org.example.quanlyheo.service;

import org.example.quanlyheo.model.Origin;
import org.example.quanlyheo.repository.IOriginRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginService implements IOriginService{
    private IOriginRepository originRepository;

    public OriginService(IOriginRepository originRepository) {
        this.originRepository = originRepository;
    }

    @Override
    public List<Origin> findAll() {
        return originRepository.findAll();
    }
}
