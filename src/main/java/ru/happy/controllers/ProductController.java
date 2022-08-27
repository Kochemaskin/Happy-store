package ru.happy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.happy.dto.ProductDto;
import ru.happy.exceptions.ResourceNotFoundException;
import ru.happy.repositories.specifications.ProductSpecification;
import ru.happy.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam MultiValueMap<String, String> params,
                                           @RequestParam(defaultValue = "0", name = "page") Integer page,
                                           @RequestParam(defaultValue = "3", name = "count") Integer count) {

        return productService.getProductPage(ProductSpecification.build(params), page, count);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.saveOrUpdate(productDto);
    }

    @PutMapping
    public ProductDto modifiedProduct(@RequestBody ProductDto productDto) {
        if (productDto.getId() != null) {
            productService.getProductById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product by ID: " + productDto.getId() + " not found"));
            return productService.saveOrUpdate(productDto);
        }
        return addProduct(productDto);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductDtoById(id).orElseThrow(() -> new ResourceNotFoundException("Product by ID: " + id + " not found"));
    }


    @DeleteMapping
    public void deleteProductById(@RequestParam Long id) {
        productService.deleteProductById(id);
    }
}
