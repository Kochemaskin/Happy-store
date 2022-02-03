package ru.happy.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.happy.converters.ProductConverter;
import ru.happy.dto.ProductDto;
import ru.happy.entities.Product;
import ru.happy.exceptions.ResourceNotFoundException;
import ru.happy.services.ProductService;
import ru.happy.validators.ProductValidator;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(minPrice, maxPrice, titlePart, page).map(
                p -> productConverter.entityToDto(p)
        );
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}