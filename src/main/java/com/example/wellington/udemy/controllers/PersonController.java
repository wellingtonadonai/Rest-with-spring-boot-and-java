package com.example.wellington.udemy.controllers;

import com.example.wellington.udemy.controllers.docs.PersonControllerDocs;
import com.example.wellington.udemy.data.dto.PersonDTO;
import com.example.wellington.udemy.service.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name ="People", description = "Endipoints for Managing People")
public class PersonController implements PersonControllerDocs {
    @Autowired
    private PersonServices service;


    @GetMapping(produces = {
    MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE,
    MediaType.APPLICATION_YAML_VALUE})

    @Override
    public List<PersonDTO> findAll(){
        return service.findAll();

    }

    @Override
    @GetMapping(value = "/{id}",
    produces = {
    MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE,
    MediaType.APPLICATION_YAML_VALUE})

    public PersonDTO findById(@PathVariable("id") Long id){
        return service.findById(id);


    }
    @Override
    @PostMapping(
    consumes = {
    MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE,
    MediaType.APPLICATION_YAML_VALUE},

    produces = {
    MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE,
    MediaType.APPLICATION_YAML_VALUE})

    public PersonDTO create(@RequestBody PersonDTO Person){
        return service.create(Person);

    }

    @Override
    @PutMapping(
    consumes = {
    MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE,
    MediaType.APPLICATION_YAML_VALUE},

    produces = {
    MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE,
    MediaType.APPLICATION_YAML_VALUE})

    public PersonDTO update(@RequestBody PersonDTO person){
        return service.update(person);

    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
