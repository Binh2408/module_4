package org.example.quan_ly_khach_hang.repository;

import org.example.quan_ly_khach_hang.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> findAll();
    Customer findById(int id);

}
