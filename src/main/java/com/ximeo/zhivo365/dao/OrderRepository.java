package com.ximeo.zhivo365.dao;

import com.ximeo.zhivo365.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query(value = "select count(*) from orders where review_date is null", nativeQuery = true)
    public BigInteger countUreaded();
}