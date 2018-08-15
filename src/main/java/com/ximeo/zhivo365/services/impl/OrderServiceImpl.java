package com.ximeo.zhivo365.services.impl;

import com.google.common.collect.Lists;
import com.ximeo.zhivo365.dao.OrderPagerRepository;
import com.ximeo.zhivo365.dao.OrderRepository;
import com.ximeo.zhivo365.domain.Order;
import com.ximeo.zhivo365.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ordServ")
@Repository
@Transactional
public class OrderServiceImpl implements OrderService {
    private OrderRepository ordDAO;
    private OrderPagerRepository ordrPgrDAO;

    @Override
    public void addOrder(Order order) {
        ordDAO.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrder(Long id) {
        return ordDAO.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getOrders() {
        return Lists.newArrayList(ordDAO.findAll());
    }

    @Override
    public void delOrder(Long id) {
        ordDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Order> getOrderPage(Pageable pageable) {
        return ordrPgrDAO.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public long getUnreadedOrderCount() {
        return ordDAO.countUreaded().longValue();
    }

    @Autowired
    public void setOrdDAO(OrderRepository ordDAO) {
        this.ordDAO = ordDAO;
    }

    @Autowired
    public void setOrdrPgrDAO(OrderPagerRepository ordrPgrDAO) {
        this.ordrPgrDAO = ordrPgrDAO;
    }
}