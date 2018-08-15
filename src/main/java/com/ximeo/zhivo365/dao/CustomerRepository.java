package com.ximeo.zhivo365.dao;

import com.ximeo.zhivo365.domain.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, String> {
}
