package ru.happy.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
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

    @Column(name = "user_id")
    Long user_id;

    @JsonBackReference
    @OneToMany(mappedBy = "orderOwner")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    List<OrderItem> items;

    public Order(Long user_id) {
        this.user_id = user_id;
        items = new ArrayList<>();
    }
}