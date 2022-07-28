package ru.happy.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.happy.beans.Cart;
import ru.happy.dto.OrderDto;
import ru.happy.entities.Order;
import ru.happy.entities.OrderItem;
import ru.happy.repositories.OrderItemRepository;
import ru.happy.repositories.OrderRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserService userService;
    private final Cart cart;

    public List<OrderDto> getOrdersByUserId(Long id) {
        return orderRepository.findOrdersByUserId(id).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public List<OrderDto> getOrdersList(Principal principal) {
        return getOrdersByUserId(userService.getIdByUserName(principal.getName()));
    }

    public Optional<OrderDto> getOrderDto(int orderId, Principal principal) {
        List<OrderDto> orders = getOrdersList(principal);
        for (OrderDto o : orders) {
            if (o.getId() == orderId) {
                return Optional.of(o);
            }
        }
        return Optional.empty();
    }

    public List<OrderDto> createOrderFormCart(Principal principal) {
        if (!cart.getItems().isEmpty()) {
            Order newOrder = new Order(userService.getIdByUserName(principal.getName()));
            orderRepository.save(newOrder);
            for (OrderItem item : cart.getItems()) {
                item.setOrderOwner(newOrder);
                orderItemRepository.save(item);
            }
            cart.clear();
        }
        return getOrdersList(principal);
    }
}