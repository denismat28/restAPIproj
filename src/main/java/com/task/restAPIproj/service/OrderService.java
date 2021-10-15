package com.task.restAPIproj.service;

import com.task.restAPIproj.entity.OrderEntity;
import com.task.restAPIproj.entity.UserEntity;
import com.task.restAPIproj.exception.OrderAlreadyExistException;
import com.task.restAPIproj.exception.OrderNotFoundException;
import com.task.restAPIproj.model.Order;
import com.task.restAPIproj.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public Order createOffice(OrderEntity order) throws OrderAlreadyExistException {
        if (orderRepository.findByAddress(order.getAddress()) != null) {
            throw new OrderAlreadyExistException("Office already exist!");
        }
        return Order.toModel(orderRepository.save(order));
    }

    public Order changeInfo(OrderEntity order) throws OrderNotFoundException {
        if (orderRepository.findByAddress(order.getAddress()) == null) {
            throw new OrderNotFoundException("Office with such an address does not exist! Plz, create new one!");
        }
        OrderEntity changeOrder = orderRepository.findByAddress(order.getAddress());
        changeOrder.setDescription(order.getDescription());
        return Order.toModel(changeOrder);
    }

    public Page<OrderEntity> getOrders(Pageable page) {
        return orderRepository.findAll(page);
    }

    public Long delete(Long id) {
        orderRepository.deleteById(id);
        return id;
    }
}
