package ru.happy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.happy.entities.Product;

@Data
@NoArgsConstructor
public class ProductDto {

    private long id;
    private String title;
    private Integer price;

    public ProductDto(Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
