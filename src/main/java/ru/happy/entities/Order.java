package ru.happy.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_user_id")
    private User user;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy = "orderOwner")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "delivery_address")
    private String delivery_address;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Order(Cart cart, User user) {
        this.user = user;
        items = new ArrayList<>();
        this.price = cart.getPrice();
        cart.getItems().stream().map(OrderItem::new).forEach(oi -> {
            oi.setOrderOwner(this);
            this.items.add(oi);
        });
    }
}