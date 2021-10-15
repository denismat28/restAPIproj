package com.task.restAPIproj.model;


import com.task.restAPIproj.entity.PassportEntity;
import com.task.restAPIproj.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    private Long id;
    private String number;
    private UserEntity user;

    public static Passport toModel(PassportEntity entity) {
        Passport model = new Passport();
        model.setId(entity.getId());
        model.setNumber(entity.getNumber());
        model.setUser(entity.getUser());
        return model;
    }
}
