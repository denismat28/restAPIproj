package com.task.restAPIproj.controller;

import com.task.restAPIproj.entity.OrderEntity;
import com.task.restAPIproj.entity.UserEntity;
import com.task.restAPIproj.exception.UserAlreadyExistException;
import com.task.restAPIproj.exception.UserNotFoundException;
import com.task.restAPIproj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok("User was saved!");
            }catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }

    @GetMapping("/getAllUsers")
    public Page<UserEntity> getAllUsers(Pageable page) {
        return userService.getUsers(page);
    }

    @GetMapping("/ordered-users")
    public Page<UserEntity> getOrderedUsers(@PageableDefault (sort = "id", size = 20) Pageable page) {
        return userService.getUsers(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }

    @PutMapping
    public ResponseEntity changeUser(@RequestBody UserEntity user) {
        try {
            return ResponseEntity.ok(userService.changeUser(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }

}
