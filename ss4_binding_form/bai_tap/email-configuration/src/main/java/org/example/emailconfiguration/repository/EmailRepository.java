package org.example.emailconfiguration.repository;

import org.example.emailconfiguration.model.Email;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class EmailRepository implements IEmailRepository{
    private static final List<Email> emails = new ArrayList<>();
    static {
        emails.add(new Email("English",20,true,"Abc"));
    }
    @Override
    public List<Email> findAll() {
        return emails;
    }

    @Override
    public void add(Email email) {
        emails.add(email);
    }

    @Override
    public Email findById(String id) {
        for (Email email: emails) {
            if(email.getLanguages().equals(id)) {
                return email;
            }
        }
        return null;
    }

    @Override
    public void save(Email email) {
        Email email1 = findById(email.getLanguages());
        email1.setLanguages(email.getLanguages());
        email1.setPageSize(email.getPageSize());
        email1.setSpams(email.isSpams());
        email1.setSignature(email.getSignature());
    }
}
