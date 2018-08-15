package com.ximeo.zhivo365.services.impl;

import com.google.common.collect.Lists;
import com.ximeo.zhivo365.dao.CustomerRepository;
import com.ximeo.zhivo365.domain.Customer;
import com.ximeo.zhivo365.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("custServ")
@Repository
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository custDAO;

    @Override
    @Transactional(readOnly = true)
    public Customer getById(String phone) {
        return custDAO.findOne(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAll() {
        return Lists.newArrayList(custDAO.findAll());
    }

    @Override
    public void delCustomer(String phone) {
        custDAO.delete(phone);
    }

    @Autowired
    public void setCustDAO(CustomerRepository custDAO) {
        this.custDAO = custDAO;
    }
}