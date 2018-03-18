package com.marcsystem.std.company.model;

import javax.persistence.*;

@Entity
public class OrderProduct implements IEntity<Long>{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="order_id")
    private Long orders;

    @Column(name="product_id")
    private Long product;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }
}
