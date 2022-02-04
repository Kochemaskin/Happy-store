package ru.happy.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.happy.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private List<OrderItemDto> items;
    private int totalPrice;
    private String address;
    private String createdAt;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.address = order.getAddress();
        this.createdAt = order.getCreatedAt().toString();
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
