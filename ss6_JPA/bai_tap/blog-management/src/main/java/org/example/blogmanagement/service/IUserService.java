package org.example.blogmanagement.service;

import org.example.blogmanagement.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
}
