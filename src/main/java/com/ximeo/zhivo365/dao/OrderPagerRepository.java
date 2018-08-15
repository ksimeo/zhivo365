package com.ximeo.zhivo365.dao;

import com.ximeo.zhivo365.domain.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderPagerRepository extends PagingAndSortingRepository<Order, Long> {
}
