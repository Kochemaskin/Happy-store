package ru.happy.converters;

import org.springframework.stereotype.Component;
import ru.happy.entities.Product;
import ru.happy.entities.dto.ProductDto;

@Component
public class ProductConverter {

    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getPrice());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getPrice());
    }
}
