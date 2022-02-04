package ru.happy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.happy.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUsername(String userName);
//    @Query(value = "select * from orders o where o.user_id=:uid", nativeQuery = true)
//    List<Order> findOrdersByUserId(@Param("uid") Long uid);
}


