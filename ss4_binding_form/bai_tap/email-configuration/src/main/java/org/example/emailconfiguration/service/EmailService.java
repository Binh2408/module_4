package org.example.emailconfiguration.service;

import org.example.emailconfiguration.model.Email;
import org.example.emailconfiguration.repository.IEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmailService implements IEmailService{
    private final IEmailRepository emailRepository;
    @Autowired
    public EmailService(IEmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    @Override
    public void add(Email email) {
        emailRepository.add(email);
    }

    @Override
    public Email findById(int id) {
        return emailRepository.findById(id);
    }

    @Override
    public void save(Email email) {
        emailRepository.save(email);
    }
}
