package com.task.restAPIproj.repository;

import com.task.restAPIproj.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

}
