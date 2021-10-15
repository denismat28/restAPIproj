package com.task.restAPIproj.controller;

import com.task.restAPIproj.entity.PassportEntity;
import com.task.restAPIproj.entity.ProjectEntity;
import com.task.restAPIproj.entity.UserEntity;
import com.task.restAPIproj.exception.ProjectAlreadyExistException;
import com.task.restAPIproj.exception.ProjectNotFoundException;
import com.task.restAPIproj.exception.UserAlreadyExistException;
import com.task.restAPIproj.exception.UserNotFoundException;
import com.task.restAPIproj.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity creationProj(@RequestBody ProjectEntity proj) {
        try {
            projectService.creation(proj);
            return ResponseEntity.ok("Project was saved!");
        }catch (ProjectAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }


    @GetMapping
    public ResponseEntity getOneProj(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(projectService.getOne(id));
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }

    @GetMapping("/getAll")
    public Page<ProjectEntity> getAllById(Pageable page) {
        return projectService.getAll(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id){
        try {
            return ResponseEntity.ok(projectService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }


    @PutMapping
    public ResponseEntity changeProject(@RequestBody ProjectEntity proj) {
        try {
            return ResponseEntity.ok(projectService.changeTitle(proj));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }
}
