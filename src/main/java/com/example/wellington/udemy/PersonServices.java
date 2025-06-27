package com.example.wellington.udemy;

import com.example.wellington.udemy.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong couter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Finding one Person");
        Person person = new Person();

        person.setId(couter.incrementAndGet());
        person.setFirstname("Wellington");
        person.setLasttname ("Oliveira");
        person.setAdress("Rua Jose Lopes");
        person.setGender("Male");

        return person;

    }

}
