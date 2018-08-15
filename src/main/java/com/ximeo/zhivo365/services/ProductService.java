package com.ximeo.zhivo365.services;

import com.ximeo.zhivo365.domain.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product prod);
    List<Product> getAll();
    Product getById(long id);
    void delProduct(long id);
}
