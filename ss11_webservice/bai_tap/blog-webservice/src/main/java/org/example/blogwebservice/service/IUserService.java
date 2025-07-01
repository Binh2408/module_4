package org.example.blogwebservice.service;

import org.example.blogwebservice.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
}
