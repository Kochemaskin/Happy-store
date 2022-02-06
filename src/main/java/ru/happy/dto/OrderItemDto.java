package ru.happy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.happy.entities.OrderItem;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getProduct().getId();
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
    }
}
