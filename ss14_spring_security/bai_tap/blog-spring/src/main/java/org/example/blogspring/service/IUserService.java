package org.example.blogspring.service;

import org.example.blogspring.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findByUsername(String username);
}
