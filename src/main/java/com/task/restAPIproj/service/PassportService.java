package com.task.restAPIproj.service;

import com.task.restAPIproj.entity.PassportEntity;
import com.task.restAPIproj.exception.PassportAlreadyExistException;
import com.task.restAPIproj.exception.PassportNotFoundException;
import com.task.restAPIproj.model.Passport;
import com.task.restAPIproj.repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassportService {

    @Autowired
    private PassportRepository passportRepository;

    public PassportEntity createPassport(PassportEntity passport) throws PassportAlreadyExistException {
        if (passportRepository.findByNumber(passport.getNumber()) != null) {
            throw new PassportAlreadyExistException("Passport already exist!");
        }
        return passportRepository.save(passport);
    }

    public Passport getOne(Long id) throws PassportNotFoundException {
        PassportEntity userDet = passportRepository.findById(id).get();
        if(userDet == null){
            throw new PassportNotFoundException("Passport not found!");
        }
        return Passport.toModel(userDet);
    }

    public Long delete(Long id) {
        passportRepository.deleteById(id);
        return id;
    }


    public Passport changeInfo(PassportEntity passportEntity) throws PassportNotFoundException {
        if (passportRepository.findById(passportEntity.getId()) == null) {
            throw new PassportNotFoundException("Passport with such number does not exist! Plz, create new one!");
        }
        PassportEntity psp = passportRepository.getById(passportEntity.getId());
        psp.setNumber(passportEntity.getNumber());
        return Passport.toModel(psp);
    }
}
