package org.example.blogmanagement.repository;

import org.example.blogmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
}
