package com.task.restAPIproj.service;


import com.task.restAPIproj.entity.UserEntity;
import com.task.restAPIproj.exception.UserAlreadyExistException;
import com.task.restAPIproj.exception.UserNotFoundException;
import com.task.restAPIproj.model.User;
import com.task.restAPIproj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepository.findUserEntityByName(user.getName()) != null) {
            throw new UserAlreadyExistException("User already exist!");
        }
        return userRepository.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("User not found!");
        }
        return User.toModel(user);
    }

    public Long delete(Long id) {
        userRepository.deleteById(id);
        return id;
    }


    public Page<UserEntity> getUsers(Pageable page) {
        return userRepository.findAll(page);
    }


    public User changeUser(UserEntity user) throws UserNotFoundException {
        if (userRepository.findUserEntityByName(user.getName()) == null) {
            throw new UserNotFoundException("User does not exist! Plz, create new one!");
        }
        UserEntity usr = userRepository.findUserEntityByName(user.getName());
        usr.setPassport(user.getPassport());
        usr.setSalary(user.getSalary());
        return User.toModel(usr);
    }
}