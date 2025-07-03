package org.example.phonemanagement.service;

import org.example.phonemanagement.model.SmartPhone;

import java.util.List;
import java.util.Optional;

public interface ISmartPhoneService {
    List<SmartPhone> findAll();
    SmartPhone findById(Long id);
    void save(SmartPhone smartPhone);
    void remove(Long id);
}
