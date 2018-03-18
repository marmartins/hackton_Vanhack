package com.marcsystem.std.company.controller;

import com.marcsystem.std.company.model.OrderProduct;
import com.marcsystem.std.company.model.Orders;
import com.marcsystem.std.company.model.Product;
import com.marcsystem.std.company.repository.OrderProductRepository;
import com.marcsystem.std.company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@RestController
public class OrderProductController extends BaseController<OrderProduct, Long, OrderProductRepository> {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orderProducts/{orderId}")
    public List<OrderProduct> getProductsByOrder(@PathVariable Long orderId) {
        Orders order = orderRepository.getOne(orderId);
        if (nonNull(order)) {
            return order.getProducts();
        }
        return new ArrayList<>();
    }

    @DeleteMapping("/orderProducts/{orderProductId}")
    public void delProduct(@PathVariable Long orderProductId) {
        delete(orderProductId);
    }

    @PostMapping("/orderProducts/{orderId}/{productId}")
    public ResponseEntity<Product> addNewProduct(@PathVariable Long orderId,
                                                 @PathVariable Long productId) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrders(orderId);
        orderProduct.setProduct(productId);
        OrderProduct saved = save(orderProduct);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
