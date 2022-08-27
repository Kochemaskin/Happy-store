package ru.happy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.happy.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private int totalPrice;
    private String delivery_address;
    private String creationDateTime;
    private List<OrderItemDto> items;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.totalPrice = order.getPrice();
        this.delivery_address = order.getDelivery_address();
        this.creationDateTime = order.getCreatedAt().toString();
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
