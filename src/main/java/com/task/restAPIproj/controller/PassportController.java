package com.task.restAPIproj.controller;


import com.task.restAPIproj.entity.PassportEntity;
import com.task.restAPIproj.exception.PassportAlreadyExistException;
import com.task.restAPIproj.exception.PassportNotFoundException;
import com.task.restAPIproj.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passports")
public class PassportController {

    @Autowired
    private PassportService passportService;

    @PostMapping
    public ResponseEntity createDetails(@RequestBody PassportEntity passportEntity) {
        try {
            passportService.createPassport(passportEntity);
            return ResponseEntity.ok("Passport was saved!");
        }catch (PassportAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }


    @GetMapping
    public ResponseEntity getOne(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(passportService.getOne(id));
        } catch (PassportNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePassport(@PathVariable Long id){
        try {
            return ResponseEntity.ok(passportService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }

    @PutMapping
    public ResponseEntity changePassport(@RequestBody PassportEntity passportEntity) {
        try {
            return ResponseEntity.ok(passportService.changeInfo(passportEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request!");
        }
    }
}
