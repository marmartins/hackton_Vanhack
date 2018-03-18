package com.marcsystem.std.company.controller;

import com.marcsystem.std.company.model.Orders;
import com.marcsystem.std.company.model.Product;
import com.marcsystem.std.company.model.Status;
import com.marcsystem.std.company.repository.OrderProductRepository;
import com.marcsystem.std.company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
public class OrderController extends BaseController<Orders, Long, OrderRepository> {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @GetMapping("/orders")
    public List<Orders> getAllOrders() {
        return getAll();
    }

    @GetMapping("/orders/{id}")
    public Orders getOneOrder(@PathVariable Long id) {
        return getOne(id);
    }

    @DeleteMapping("/orders/{id}")
    public void delOrder(@PathVariable Long id) {
        delete(id);
    }

    @PostMapping("/orders")
    public ResponseEntity<Product> createNew(@RequestBody Orders orders) {
        Orders saved = save(orders);

        saved.getProducts().forEach(product -> {
            product.setOrders(saved.getId());
            orderProductRepository.save(product);
        });

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<Product> orderCancel(@PathVariable Long orderId) {
        Orders orders = getOne(orderId);
        if (isNull(orders)) {
            return ResponseEntity.notFound().build();
        }

        orders.setStatus(Status.CANCELED);
        save(orders);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("orders/{id}")
    public ResponseEntity<Product> updateOrder(@RequestBody Orders orders, @PathVariable Long id) {
        Orders old = getOne(id);
        if (isNull(old)) {
            return ResponseEntity.notFound().build();
        }

        orders.setId(id);
        save(orders);

        return ResponseEntity.noContent().build();
    }
}
