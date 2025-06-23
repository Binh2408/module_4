package org.example.customermanagementorm.repository;

import org.example.customermanagementorm.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer);
    void update(int id, Customer customer);

    void remove(int id);
}
