package com.ximeo.zhivo365.dao;

import com.ximeo.zhivo365.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
