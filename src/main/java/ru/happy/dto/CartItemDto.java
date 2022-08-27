package ru.happy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.happy.entities.CartItem;

@Data
@NoArgsConstructor
public class CartItemDto {

    private Long id;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public CartItemDto(CartItem cartItem) {

        this.id = cartItem.getProduct().getId();
        this.productTitle = cartItem.getProduct().getTitle();
        this.quantity = cartItem.getQuantity();
        this.pricePerProduct = cartItem.getPricePerProduct();
        this.price = cartItem.getPrice();
    }
}
