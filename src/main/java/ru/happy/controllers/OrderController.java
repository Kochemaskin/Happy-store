package ru.happy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.happy.beans.DeliveryAddress;
import ru.happy.dto.OrderDto;
import ru.happy.entities.User;
import ru.happy.exceptions.ResourceNotFoundException;
import ru.happy.services.OrderService;
import ru.happy.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getOrders(Principal principal) {
        return orderService.findAllOrdersByOwnerName(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

//    @GetMapping("/{id}")
//    public Optional<OrderDto> getOrders(@PathVariable Integer id, Principal principal) {
//        return orderService.getOrderDto(id, principal);
//    }

    @PostMapping
    public void addOrderFormCart(@RequestBody DeliveryAddress deliveryAddress, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createOrderFormCart(user, deliveryAddress);
    }

}
