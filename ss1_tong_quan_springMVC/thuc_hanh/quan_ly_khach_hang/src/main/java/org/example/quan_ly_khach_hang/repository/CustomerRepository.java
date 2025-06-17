package org.example.quan_ly_khach_hang.repository;

import org.example.quan_ly_khach_hang.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository implements ICustomerRepository{
    private static final List<Customer> customerList = new ArrayList<>();
    static {
        customerList.add(new Customer(1, "Nguyen Van A", "a@gmail.com", "Ha Noi"));
        customerList.add(new Customer(2, "Tran Thi B", "b@gmail.com", "Da Nang"));
        customerList.add(new Customer(3, "Le Van C", "c@gmail.com", "Ho Chi Minh"));
        customerList.add(new Customer(4, "Pham Thi D", "d@gmail.com", "Can Tho"));
        customerList.add(new Customer(5, "Hoang Van E", "e@gmail.com", "Hai Phong"));
    }
    @Override
    public List<Customer> findAll() {
        return customerList;
    }

    @Override
    public Customer findById(int id) {
        for (Customer customer : customerList) {
            if (customer.getIdCus() == id) {
                return customer;
            }
        }
        return null;
    }
}
