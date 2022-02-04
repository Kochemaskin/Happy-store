package ru.happy.validators;

import org.springframework.stereotype.Component;
import ru.happy.entities.dto.ProductDto;
import ru.happy.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if(productDto.getPrice() < 1) {
            errors.add("Price cannot be less 1.");
        }
        if(productDto.getName().isBlank()){
            errors.add("Product must have name.");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
