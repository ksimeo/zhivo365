package com.ximeo.zhivo365.services;

import com.ximeo.zhivo365.domain.Customer;

import java.util.List;

public interface CustomerService {

    Customer getById(String phone);
    List<Customer> getAll();
    void delCustomer(String phone);
}