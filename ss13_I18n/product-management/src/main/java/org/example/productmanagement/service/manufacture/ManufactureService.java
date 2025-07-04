package org.example.productmanagement.service.manufacture;

import org.example.productmanagement.model.Manufacture;
import org.example.productmanagement.repository.IManufactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactureService implements IManufactureService{
    private final IManufactureRepository manufactureRepository;

    @Autowired
    public ManufactureService(IManufactureRepository manufactureRepository) {
        this.manufactureRepository = manufactureRepository;
    }

    @Override
    public List<Manufacture> findAll() {
        return manufactureRepository.findAll();
    }

    @Override
    public Page<Manufacture> findAll(Pageable pageable) {
        return manufactureRepository.findAll(pageable);
    }

    @Override
    public Manufacture findById(Long id) {
        return manufactureRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Manufacture manufacture) {
        manufactureRepository.save(manufacture);
    }

    @Override
    public void remove(Long id) {
        manufactureRepository.deleteById(id);
    }
}
