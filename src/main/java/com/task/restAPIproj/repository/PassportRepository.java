package com.task.restAPIproj.repository;

import com.task.restAPIproj.entity.PassportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PassportRepository extends JpaRepository<PassportEntity, Long> {
    PassportEntity findByNumber(String number);
}
