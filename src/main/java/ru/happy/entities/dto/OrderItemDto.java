package ru.happy.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.happy.entities.OrderItem;

@NoArgsConstructor
@Data
public class OrderItemDto {

    private Long id;
    private String productName;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getProduct().getId();
        this.productName = orderItem.getProduct().getName();
        this.quantity = orderItem.getQuantity();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
    }
}