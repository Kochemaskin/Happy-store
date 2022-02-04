package ru.happy.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.happy.beans.Cart;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Table(name = "orders")
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<OrderItem> items;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Order(Cart cart, User user) {
        this.user = user;
        items = new ArrayList<>();
        this.totalPrice = cart.getTotalPrice();
        cart.getItems().forEach(oi -> {
            oi.setOrderOwner(this);
            this.items.add(oi);
        });
    }
}