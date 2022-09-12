package ru.happy.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.happy.beans.Cart;
import ru.happy.beans.DeliveryAddress;
import ru.happy.entities.Order;
import ru.happy.entities.User;
import ru.happy.repositories.OrderRepository;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final Cart cart;

    public Order createOrderFormCart(User user, DeliveryAddress deliveryAddress) {
        Order newOrder = new Order(cart, user);
        newOrder.setDelivery_address(deliveryAddress.getAddress());
        orderRepository.save(newOrder);
        cart.clear();
        return newOrder;
    }

    public List<Order> findAllOrdersByOwnerName(String userName) {
        return orderRepository.findAllByUserUsername(userName);
    }
}