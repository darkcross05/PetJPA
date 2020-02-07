package com.example.demo.controller;

import com.example.demo.entity.PetEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetControllerTest {

    @MockBean
    private String ResourceUrl = "http://localhost:8080/pet";
    private TestRestTemplate testRestTemplate;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        testRestTemplate = new TestRestTemplate();
        mapper = new ObjectMapper();
    }

    @Test
    void methodShouldGetAllPets() throws JsonProcessingException {
        ResponseEntity<String> response = testRestTemplate.getForEntity(ResourceUrl, String.class);
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.get(0).path("name");

        assertNotNull(name.asText());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void methodShouldFindUserById() {
        PetEntity response = testRestTemplate.getForObject(ResourceUrl + "/1", PetEntity.class);

        assertNotNull(response.getName());
        assertEquals(response.getName(), "Luis");
    }

    @Test
    void methodShouldAddSaveUser() {
        HttpEntity<PetEntity> request = new HttpEntity<>(new PetEntity("Luis", "Person"));

        PetEntity response = testRestTemplate.postForObject(ResourceUrl, request, PetEntity.class);

        assertNotNull(response);
        assertEquals(response.getName(), "Luis");
    }

    @Test
    void methodShouldDeleteUser() {
        testRestTemplate.delete(ResourceUrl + "/1");
        PetEntity response = testRestTemplate.getForObject(ResourceUrl + "/1", PetEntity.class);
        assertNull(response.getName());
        assertNull(response.getType());
    }
}