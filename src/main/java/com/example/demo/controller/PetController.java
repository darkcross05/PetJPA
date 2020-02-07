package com.example.demo.controller;

import com.example.demo.entity.PetEntity;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pet")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping()
    public Iterable<PetEntity> getAll() {
        return petService.getAll();
    }

    @GetMapping("/{id}")
    public PetEntity getById(@PathVariable Long id) {
        PetEntity pet = petService.getById(id).orElseThrow();
        return pet;
    }

    @PostMapping()
    public PetEntity save(@RequestBody final PetEntity pet) {
        return petService.save(pet);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        return petService.delete(id);
    }

    @PutMapping("/{id}")
    public HttpStatus updateById(@PathVariable Long id, @RequestBody PetEntity pet) {
        return petService.update(id, pet);
    }

}
