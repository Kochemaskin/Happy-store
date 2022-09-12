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
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        for (OrderItemDto item : this.items) {
        totalPrice += item.getPricePerProduct() * item.getQuantity();
        }
    }
}
