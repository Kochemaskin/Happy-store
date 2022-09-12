package ru.happy.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@Slf4j
@Data
public class Cart {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> items;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User user;

    public void recalculate() {
        price = 0;
        for (CartItem item : items) {
            price += item.getPrice();
        }
    }

    public void merge(Cart otherCart) {
        int num;
        for (CartItem ci : otherCart.items) {
            if ((num = items.indexOf(ci)) != -1) {
                items.get(num).incrementQuantity(ci.getQuantity());
            } else {
                items.add(ci);
                ci.setCart(this);
            }
        }
        recalculate();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", items=" + items +
                ", itemsCount=" + items.size() +
                ", price=" + price +
                '}';
    }
}
