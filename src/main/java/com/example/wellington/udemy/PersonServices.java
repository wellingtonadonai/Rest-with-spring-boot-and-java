package com.example.wellington.udemy;

import com.example.wellington.udemy.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong couter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){
        logger.info("Finding all People");
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Person person = MockPerson(i);
            persons.add(person);
        }
        return persons;


    }



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

    public Person create(Person person){
        logger.info("create one Person");
        return person;

    }

    public Person update(Person person) {
        logger.info("update one Person");
        return person;
    }
    public void delete (String id) {
        logger.info("delete one Person");
    }

        private Person MockPerson(int i) {
            Person person = new Person();

            person.setId(couter.incrementAndGet());
            person.setFirstname("Firstname " + i);
            person.setLasttname ("Lasttname " + i);
            person.setAdress("Adress " + i);
            person.setGender("Gender " + i);

            return person;
        }


}

