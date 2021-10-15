package com.task.restAPIproj.repository;

import com.task.restAPIproj.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findByAddress(String address);
}
