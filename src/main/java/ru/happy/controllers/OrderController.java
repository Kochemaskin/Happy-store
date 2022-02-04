package ru.happy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.happy.beans.Address;
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

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public List<OrderDto> getOrders(Principal principal) {
        return orderService.findAllOrdersByOwnerName(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }


    @PostMapping
    public void addOrderFormCart(@RequestBody Address address, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createOrderFormCart(user, address);
    }

}
