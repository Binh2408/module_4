package org.example.extendblogmanagement.repository;

import org.example.extendblogmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
}
