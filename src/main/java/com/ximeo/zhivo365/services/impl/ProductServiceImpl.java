package com.ximeo.zhivo365.services.impl;

import com.google.common.collect.Lists;
import com.ximeo.zhivo365.dao.ProductRepository;
import com.ximeo.zhivo365.domain.Product;
import com.ximeo.zhivo365.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("prodServ")
@Repository
@Transactional
public class ProductServiceImpl implements ProductService {
    ProductRepository prodDAO;

    @Override
    public void addProduct(Product prod) {
        prodDAO.save(prod);
    }

    @Transactional(readOnly = true)
    @Override
    public Product getById(long id) {
        return prodDAO.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAll() {
        return Lists.newArrayList(prodDAO.findAll());
    }

    @Override
    public void delProduct(long id) {
        prodDAO.delete(id);
    }

    @Autowired
    public void setProdDAO(ProductRepository prodDAO) {
        this.prodDAO = prodDAO;
    }
}