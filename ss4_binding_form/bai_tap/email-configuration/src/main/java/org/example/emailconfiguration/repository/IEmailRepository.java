package org.example.emailconfiguration.repository;

import org.example.emailconfiguration.model.Email;

import java.util.List;

public interface IEmailRepository {
    List<Email> findAll();
    void add(Email email);
    Email findById(int id);
    void save(Email email);

}
