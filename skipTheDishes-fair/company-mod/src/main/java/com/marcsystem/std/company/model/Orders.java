package com.marcsystem.std.company.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orders extends AuditableEntity<Long> {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    private String code;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "orders")
    private List<OrderProduct> products = new ArrayList<>();

    public Orders() {
        this.status = Status.OPEN;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

}
