package com.example.wellington.udemy.controllers;

import com.example.wellington.udemy.data.dto.PersonDTO;
import com.example.wellington.udemy.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test/v1")
public class PersonController {
    @Autowired
    private PersonServices service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> findAll(){
        return service.findAll();

    }

    @GetMapping(value = "/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findById(@PathVariable("id")Long id){
        return service.findById(id);

    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO create (@RequestBody PersonDTO Person){
        return service.create(Person);

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO update (@RequestBody PersonDTO person){
        return service.update(person);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
