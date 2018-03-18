package com.marcsystem.std.company.controller;

import com.marcsystem.std.company.model.Product;
import com.marcsystem.std.company.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
public class ProductController extends BaseController<Product, Long, ProductRepository> {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return getAll();
    }

    @GetMapping("/products/{id}")
    public Product getOneProduct(@PathVariable Long id){
        return getOne(id);
    }

    @DeleteMapping("/products/{id}")
    public void delProduct(@PathVariable Long id) {
        delete(id);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createNew(@RequestBody Product product) {
        Product saved = save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        Product old = getOne(id);
        if (isNull(old)) {
            return ResponseEntity.notFound().build();
        }

        product.setId(id);
        save(product);

        return ResponseEntity.noContent().build();
    }
}
