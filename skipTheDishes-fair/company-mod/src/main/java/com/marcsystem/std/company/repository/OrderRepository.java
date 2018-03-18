package com.marcsystem.std.company.repository;

import com.marcsystem.std.company.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{
}
