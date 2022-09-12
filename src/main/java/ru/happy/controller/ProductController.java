package ru.happy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.happy.entity.Product;
import ru.happy.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*
     * GET: http://localhost:8855/happy/products/
     *      http://localhost:8855/happy/products/?min=51
     *      http://localhost:8855/happy/products/?max=100
     *      http://localhost:8855/happy/products/?min=51&max=100
     */
    @GetMapping("/")
    public List<Product> productList(@RequestParam(value = "min", defaultValue = "0") BigDecimal min,
                                     @RequestParam(value = "max", defaultValue = "999999") BigDecimal max) {
        return productService.findAll(min, max);
    }

    /*
     * GET: http://localhost:8855/happy/products/1
     */
    @GetMapping("/{id}")
    public Product product(@PathVariable Long id) {
        Product product = productService.findById(id);

        if (product == null) {
            throw new RuntimeException("Product with id " + id + " not found");
        }

        return product;
    }

    /*
     * POST: http://localhost:8855/products/
     * BODY:
     *       {
     *          "name": "Waterlemon",
     *          "price": 250
     *       }
     */
    @PostMapping("/")
    public Product addProduct(@RequestBody Product product) {
        productService.save(product);

        return product;
    }

    /*
     * PUT: http://localhost:8855/products/
     * BODY:
     *       {
     *          "id": 5,
     *          "name": "Waterlemon",
     *          "price": 150
     *       }
     */
    @PutMapping("/")
    public Product updProduct(@RequestBody Product product) {
        productService.save(product);

        return product;
    }

    /*
     * DELETE: http://localhost:8855/products/5
     */
    @DeleteMapping("/{id}")
    public Product delProduct(@PathVariable Long id) {
        return productService.deleteById(id);
    }
}
