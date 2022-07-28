package ru.happy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.happy.dto.OrderDto;
import ru.happy.services.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getOrders(Principal principal) {
        return orderService.getOrdersList(principal);
    }

    @GetMapping("/{id}")
    public Optional<OrderDto> getOrders(@PathVariable Integer id, Principal principal) {
        return orderService.getOrderDto(id, principal);
    }

    @PostMapping
    public List<OrderDto> addOrderFormCart(Principal principal) {
        return orderService.createOrderFormCart(principal);
    }

}
