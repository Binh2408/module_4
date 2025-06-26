package org.example.formregister.service;

import org.example.formregister.model.User;
import org.example.formregister.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
