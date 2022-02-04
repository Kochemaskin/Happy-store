package ru.happy.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.happy.entities.Product;

@NoArgsConstructor
@Data
public class ProductDto {

    private Long id;
    private String name;
    private Integer price;


    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
