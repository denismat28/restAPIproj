package com.task.restAPIproj.repository;


import com.task.restAPIproj.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByName(String name);
}
