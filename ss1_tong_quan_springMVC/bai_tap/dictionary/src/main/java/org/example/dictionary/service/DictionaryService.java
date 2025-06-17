package org.example.dictionary.service;

import org.example.dictionary.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {
    private final DictionaryRepository repository;

    @Autowired
    public DictionaryService(DictionaryRepository repository) {
        this.repository = repository;

    }
    public String translate(String word, String direction) {
        return repository.translate(word, direction);
    }

}
