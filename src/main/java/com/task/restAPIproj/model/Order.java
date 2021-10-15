package com.task.restAPIproj.model;

import com.task.restAPIproj.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String address;
    private String description;

    public static Order toModel(OrderEntity entity) {
        Order model = new Order();
        model.setId(entity.getId());
        model.setAddress(entity.getAddress());
        model.setDescription(entity.getDescription());
        return model;
    }
}
