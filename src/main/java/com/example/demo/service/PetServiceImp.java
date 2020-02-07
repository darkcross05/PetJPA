package com.example.demo.service;

import com.example.demo.entity.PetEntity;
import com.example.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PetServiceImp implements PetService {

    private PetRepository petRepository;

    @Autowired
    public PetServiceImp(PetRepository repository) {
        this.petRepository = repository;
    }

    @Override
    public Iterable<PetEntity> getAll() {
        return petRepository.findAll();
    }

    @Override
    public Optional<PetEntity> getById(Long id) {
        return petRepository.findById(id);
    }

    @Override
    public PetEntity save(PetEntity pet) {
        return petRepository.save(pet);
    }

    @Override
    public HttpStatus delete(Long id) {
        petRepository.findById(id).ifPresent(pet -> petRepository.delete(pet));
        return HttpStatus.ACCEPTED;
    }

    @Override
    public HttpStatus update(Long id, PetEntity pet) {
        petRepository.findById(id).ifPresent(pet1 -> {
            pet.setId(id);
            petRepository.save(pet);
        });
        return HttpStatus.ACCEPTED;
    }

}
