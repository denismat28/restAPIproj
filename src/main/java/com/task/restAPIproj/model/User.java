package com.task.restAPIproj.model;

import com.task.restAPIproj.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private double salary;
    private List<Order> orders;

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSalary(entity.getSalary());
        model.setOrders(entity.getOrders().stream().map(Order::toModel).collect(Collectors.toList()));
        return model;
    }
}