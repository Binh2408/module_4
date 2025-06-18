package org.example.managementcustomer.service;

import org.example.managementcustomer.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);
}
