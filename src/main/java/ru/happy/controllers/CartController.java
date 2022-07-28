package ru.happy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.happy.beans.Cart;
import ru.happy.dto.CartDto;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final Cart cart;

    @GetMapping
    public CartDto getCart() {
        return new CartDto(cart);
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cart.addToCart(id);
    }

    @GetMapping("/delete/{id}")
    public void delProductById(@PathVariable Long id) {
        cart.removeFromCart(id);
    }

    @GetMapping("/delete/all/{id}")
    public void delAllProductById(@PathVariable Long id) {
        cart.removeAllProductFromCartById(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clear();
    }
}
