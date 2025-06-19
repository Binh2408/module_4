package org.example.emailconfiguration.repository;

import org.example.emailconfiguration.model.Email;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class EmailRepository implements IEmailRepository{
    private static final List<Email> emails = new ArrayList<>();
    private static int currentId = 1;
    static {
        emails.add(new Email(currentId++,"English",25,true,"Abc"));
        emails.add(new Email(currentId++,"Vietnamese",100,true,"Abc"));
        emails.add(new Email(currentId++,"Chinese",50,true,"Abc"));
    }
    @Override
    public List<Email> findAll() {
        return emails;
    }

    @Override
    public void add(Email email) {
        email.setId(currentId++);
        emails.add(email);
    }

    @Override
    public Email findById(int id) {
        for (Email email: emails) {
            if(email.getId()== id) {
                return email;
            }
        }
        return null;
    }

    @Override
    public void save(Email email) {
        Email existing = findById(email.getId());
        if (existing != null) {
            existing.setLanguages(email.getLanguages());
            existing.setPageSize(email.getPageSize());
            existing.setSpams(email.isSpams());
            existing.setSignature(email.getSignature());
        }
    }
}
