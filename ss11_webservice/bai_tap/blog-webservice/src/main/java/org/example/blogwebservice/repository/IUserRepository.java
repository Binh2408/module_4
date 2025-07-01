package org.example.blogwebservice.repository;

import org.example.blogwebservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
}
