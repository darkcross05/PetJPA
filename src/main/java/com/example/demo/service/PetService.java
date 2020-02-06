package com.example.demo.service;

import com.example.demo.entity.PetEntity;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface PetService {

    Iterable<PetEntity> getAll();

    Optional<PetEntity> getById(Long id);

    PetEntity save(PetEntity pet);

    HttpStatus delete(Long id);

    HttpStatus update(Long id, PetEntity pet);

}
