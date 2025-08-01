package com.example.wellington.udemy.service;

import com.example.wellington.udemy.data.dto.PersonDTO;
import com.example.wellington.udemy.exceptions.ResourceNotFoundException;
import static com.example.wellington.udemy.mapper.ObjectMapper.parseListObject;
import static com.example.wellington.udemy.mapper.ObjectMapper.parseObject;

import com.example.wellington.udemy.mapper.custom.PersonMapper;
import com.example.wellington.udemy.model.Person;
import com.example.wellington.udemy.repositories.PersonRepositories;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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
        return parseListObject(repositories.findAll(), PersonDTO.class);


    }



    public PersonDTO findById(Long id){
        logger.info("Finding one Person");

         var entity = repositories.findById(id)
                 .orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
         return parseObject(entity, PersonDTO.class);



    }

    public PersonDTO create(PersonDTO person){
        logger.info("create one Person");
        var entity = parseObject(person, Person.class);

        return parseObject (repositories.save(entity),PersonDTO.class);

    }

    public PersonDTO update(PersonDTO person) {
        logger.info("update one Person");
        Person entity = repositories.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        entity.setFirstname(person.getFirstname());
        entity.setLasttname(person.getLastname());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        return parseObject(repositories.save(entity),PersonDTO.class);
    }
    public void delete (Long id) {
        logger.info("delete one Person");
        Person entity = repositories.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));

        repositories.delete(entity);

    }

}

