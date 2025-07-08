package com.example.wellington.udemy.service;

import com.example.wellington.udemy.exceptions.ResourceNotFoundException;
import com.example.wellington.udemy.model.Person;
import com.example.wellington.udemy.repositories.PersonRepositories;
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




    public List<Person> findAll(){
        logger.info("Finding all People");
        return repositories.findAll();


    }



    public Person findById(Long id){
        logger.info("Finding one Person");

         return repositories.findById(id)
                 .orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));



    }

    public Person create(Person person){
        logger.info("create one Person");
        return repositories.save(person);

    }

    public Person update(Person person) {
        logger.info("update one Person");
        Person entity = repositories.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
        entity.setFirstname(person.getFirstname());
        entity.setLasttname (person.getLasttname());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        return repositories.save(person);
    }
    public void delete (Long id) {
        logger.info("delete one Person");
        Person entity = repositories.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));

        repositories.delete(entity);

    }

}

