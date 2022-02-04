package ru.happy.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.happy.entities.Product;
import ru.happy.entities.dto.ProductDto;
import ru.happy.repositories.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<ProductDto> getProductDtoById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }

    public Page<ProductDto> getProductPage(Specification<Product> spec, int page, int count) {
        return productRepository.findAll(spec, PageRequest.of(page, count)).map(ProductDto::new);

    }

    public ProductDto saveOrUpdate(ProductDto productDto) {
        Product p = modelMapper.map(productDto, Product.class);
        return modelMapper.map(productRepository.save(p), ProductDto.class);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}