package ru.happy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.happy.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max);
}
