package ru.happy.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.happy.dto.OrderDto;
import ru.happy.entities.Cart;
import ru.happy.entities.Order;
import ru.happy.entities.User;
import ru.happy.exceptions.ResourceNotFoundException;
import ru.happy.repositories.OrderRepository;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;

    public Order createOrderFormCart(User user, String address, UUID uuid) {
        Cart cart = cartService.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Unable to find cart with id: " + uuid));
        Order newOrder = new Order(cart, user);
        newOrder.setDelivery_address(address);
        orderRepository.save(newOrder);
        cartService.clear(uuid);
        return newOrder;
    }

    public List<Order> findAllOrdersByOwnerName(String userName) {
        return orderRepository.findAllByUserUsername(userName);
    }

    public OrderDto findOrderDtoById(Long id, Principal principal) {
        List<Order> orderList = findAllOrdersByOwnerName(principal.getName());
        for (Order o : orderList) {
            if (o.getId() == id) {
                return new OrderDto(o);
            }
        }
        return null;    // todo remake to Optional
    }
}