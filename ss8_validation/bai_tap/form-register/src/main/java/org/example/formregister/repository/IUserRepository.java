package org.example.formregister.repository;

import org.example.formregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
}
