package org.example.customermanagementjpa.repository;

import org.example.customermanagementjpa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
}
