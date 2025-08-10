package com.example.wellington.udemy.service;

import com.example.wellington.udemy.controllers.PersonController;
import com.example.wellington.udemy.data.dto.PersonDTO;
import com.example.wellington.udemy.exceptions.ResourceNotFoundException;
import static com.example.wellington.udemy.mapper.ObjectMapper.parseListObject;
import static com.example.wellington.udemy.mapper.ObjectMapper.parseObject;

import com.example.wellington.udemy.mapper.custom.PersonMapper;
import com.example.wellington.udemy.model.Person;
import com.example.wellington.udemy.repositories.PersonRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong couter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepositories repositories;
    @Autowired
    PersonMapper converter;




    public List<PersonDTO> findAll(){
        logger.info("Finding all People");
        var persons = parseListObject(repositories.findAll(), PersonDTO.class);
        persons.forEach(this::addHateoasLinkdto);
        return persons;


    }



    public PersonDTO findById(Long id){
        logger.info("Finding one Person");

         var entity = repositories.findById(id)
                 .orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinkdto(dto);
        return dto;



    }

    public PersonDTO create(PersonDTO person){
        logger.info("create one Person");
        var entity = parseObject(person, Person.class);

        var dto = parseObject (repositories.save(entity),PersonDTO.class);
        addHateoasLinkdto(dto);
        return dto;

    }

    public PersonDTO update(PersonDTO person) {
        logger.info("update one Person");
        Person entity = repositories.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        entity.setFirstname(person.getFirstname());
        entity.setLastname(person.getLastname());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        var dto = parseObject(repositories.save(entity),PersonDTO.class);
        addHateoasLinkdto(dto);
        return dto;
    }

    public void delete (Long id) {
        logger.info("delete one Person");
        Person entity = repositories.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));

        repositories.delete(entity);

    }


    private void addHateoasLinkdto (PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findall").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
