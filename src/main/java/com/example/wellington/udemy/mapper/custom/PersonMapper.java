package com.example.wellington.udemy.mapper.custom;

import com.example.wellington.udemy.data.dto.PersonDTO;
import com.example.wellington.udemy.model.Person;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class PersonMapper {
    public PersonDTO convertEntityToDTOV2(Person person){
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setFirstname(person.getFirstname());
        dto.setLastname(person.getLasttname());
        dto.setAdress(person.getAdress());
        dto.setGender(person.getGender());
        return dto;
    }
    public Person convertDTOToEntity(PersonDTO person){
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstname(person.getFirstname());
        entity.setLasttname(person.getLastname());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());
        return entity;
    }

}