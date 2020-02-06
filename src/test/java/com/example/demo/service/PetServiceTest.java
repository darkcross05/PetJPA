package com.example.demo.service;

import com.example.demo.entity.PetEntity;
import com.example.demo.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceTest {

    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        petService = new PetServiceImp(petRepository);
    }

    @Test
    void methodShouldReturnAllThePets() {
        List<PetEntity> petList = Arrays.asList(new PetEntity(), new PetEntity());
        when(petRepository.findAll()).thenReturn(petList);

        Iterable<PetEntity> allPets = petService.getAll();

        assert allPets.iterator().hasNext();
        verify(petRepository).findAll();
    }

    @Test
    void methodShouldReturnAPetById() {
        PetEntity pet = new PetEntity();
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        Optional<PetEntity> petById = petService.getById(1L);

        Assert.notNull(petById.orElseThrow(), "El objeto no puede ser null");
        verify(petRepository).findById(1L);
    }

    @Test
    void methodShouldReturnASavedUser() {
        PetEntity pet = new PetEntity();
        when(petRepository.save(pet)).thenReturn(pet);

        PetEntity savedPet = petService.save(pet);

        Assert.notNull(savedPet, "El objeto no puede ser null");
        verify(petRepository).save(pet);
    }

    @Test
    void methodShouldReturnAnUpdatedUser() {
        PetEntity pet = new PetEntity("Juan", "Persona");
        when(petRepository.save(pet)).thenReturn(pet);

        HttpStatus updatedStatus = petService.update(1L, pet);

        assertEquals(updatedStatus, HttpStatus.ACCEPTED);
        verify(petRepository).findById(1L);
        //verify(petRepository).save(pet);
    }

    @Test
    void methodShouldDeleteUser() {
        HttpStatus deletedStatus = petService.delete(0L);

        assertEquals(deletedStatus, HttpStatus.ACCEPTED);
        verify(petRepository).findById(0L);
    }
}