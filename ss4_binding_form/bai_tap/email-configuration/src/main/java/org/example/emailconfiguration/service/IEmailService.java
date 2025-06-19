package org.example.emailconfiguration.service;

import org.example.emailconfiguration.model.Email;

import java.util.List;

public interface IEmailService {
    List<Email> findAll();
    void add(Email email);
    Email findById(int id);
    void save(Email email);
}
