package org.example.extendblogmanagement.service;

import org.example.extendblogmanagement.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
}
