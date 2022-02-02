package ru.happy.service;

import ru.happy.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<Product> findAll(BigDecimal min, BigDecimal max);

    Product findById(long id);

    void save(Product product);

    Product deleteById(long id);
}
