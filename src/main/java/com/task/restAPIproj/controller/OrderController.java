package com.task.restAPIproj.controller;

import com.task.restAPIproj.entity.OrderEntity;
import com.task.restAPIproj.model.Order;
import com.task.restAPIproj.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Api(value = "Order resources")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @ApiOperation(value = "creating order", response = Order.class)
    public ResponseEntity createOrder(@RequestBody OrderEntity office) {
        try {
            return ResponseEntity.ok(orderService.createOffice(office));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }

    @GetMapping("/getAllOrders")
    @ApiOperation(value = "show all orders", response = Pageable.class)
    public Page<OrderEntity> getAllOrders(Pageable page) {
        return orderService.getOrders(page);
    }

    @GetMapping("/ordered-orders")
    public Page<OrderEntity> getOrderedOrders(@PageableDefault(sort = "id", size = 20) Pageable page) {
        return orderService.getOrders(page);
    }

    @PutMapping
    public ResponseEntity changeOrder(@RequestBody OrderEntity office) {
        try {
            return ResponseEntity.ok(orderService.changeInfo(office));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Long id){
        try {
            return ResponseEntity.ok(orderService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }
}
