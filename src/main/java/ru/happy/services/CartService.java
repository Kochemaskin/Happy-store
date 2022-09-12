package ru.happy.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.happy.entities.Cart;
import ru.happy.entities.CartItem;
import ru.happy.entities.Product;
import ru.happy.entities.User;
import ru.happy.exceptions.ResourceNotFoundException;
import ru.happy.repositories.CartRepository;

import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final UserService userService;

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> findById(UUID id) {
        return cartRepository.findById(id);
    }

    public Optional<Cart> findByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Transactional
    public void addProductToCart(UUID uuid, Long productId) {
        Product p = productService.getProductById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + productId + " (add to cart)"));
        Cart cart = findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Unable to find cart with id: " + uuid));
        CartItem cartItem = new CartItem(p);
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(cartItem.getProduct().getId())) {
                item.incrementQuantity();
                cart.recalculate();
                return;
            }
        }
        cart.getItems().add(cartItem);
        cartItem.setCart(cart);
        cart.recalculate();
    }

    @Transactional
    public void removeFromCart(UUID uuid, Long productId) {
        Cart cart = findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Unable to find cart with id: " + uuid));

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                item.decrementQuantity();
                if (item.getQuantity() <= 0) {
                    cart.getItems().remove(item);
                    item.setCart(null);
                }
                cart.recalculate();
                return;
            }
        }
    }

    @Transactional
    public void deleteFromCart(UUID uuid, Long productId) {
        Cart cart = findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Unable to find cart with id: " + uuid));

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                cart.getItems().remove(item);
                item.setCart(null);
                cart.recalculate();
                return;
            }
        }
    }

    @Transactional
    public void clear(UUID uuid) {
        Cart cart = findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Unable to find cart with id: " + uuid));
        Iterator<CartItem> iterator = cart.getItems().iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            item.setCart(null);
            iterator.remove();
        }
    }

    public Cart getNewCart() {
        return cartRepository.save(new Cart());
    }

    @Transactional
    public UUID getCartForUser(String username, UUID cartUuid) {
        if (username != null && cartUuid != null) {
            User user = userService.findByUsername(username).get();
            Cart cart = findById(cartUuid).get();
            Optional<Cart> oldCart = findByUserId(user.getId());
            if (oldCart.isPresent()) {
                cart.merge(oldCart.get());
                cartRepository.delete(oldCart.get());
            }
            cart.setUser(user);
        }
        if (username == null) {
            Cart cart = save(new Cart());
            return cart.getId();
        }
        User user = userService.findByUsername(username).get();
        Optional<Cart> cart = findByUserId(user.getId());
        if (cart.isPresent()) {
            return cart.get().getId();
        }
        Cart newCart = new Cart();
        newCart.setUser(user);
        save(newCart);
        return newCart.getId();
    }
}

