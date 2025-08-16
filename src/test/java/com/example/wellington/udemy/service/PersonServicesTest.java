package com.example.wellington.udemy.service;

import com.example.wellington.udemy.data.dto.PersonDTO;
import com.example.wellington.udemy.exceptions.RequiredObjectIsNullException;
import com.example.wellington.udemy.model.Person;
import com.example.wellington.udemy.repositories.PersonRepositories;
import com.example.wellington.udemy.unitetest.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson imput;

    @InjectMocks
    private PersonServices services;

    @Mock
    PersonRepositories repositories;

    @BeforeEach
    void setUp() {
        imput = new MockPerson();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void findById() {
        Person person = imput.mockEntity(1);
        person.setId(1L);
        when(repositories.findById(1L)).thenReturn(Optional.of(person));
        var result = services.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/api/person/v1/1")
                && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("create")
                                && link.getHref().endsWith("/api/person/v1/1")
                                && link.getType().equals("POST")
                        ));

        assertNotNull(result.getLinks().stream()
                                .anyMatch(link -> link.getRel().value().equals("update")
                                        && link.getHref().endsWith("/api/person/v1")
                                        && link.getType().equals("PUT")
                                ));

        assertNotNull(result.getLinks().stream()
                        .anyMatch(link -> link.getRel().value().equals("delete")
                                && link.getHref().endsWith("/api/person/v1/1")
                                && link.getType().equals("DELETE")
                        ));

        assertEquals("First Name Test1", result.getFirstname());
        assertEquals("Last Name Test1", result.getLastname());
        assertEquals("Female", result.getGender());
        assertEquals("Address Test1", result.getAdress());

    }

    @Test
    void create() {
        Person person = imput.mockEntity(1);
        Person peristed = person;
        peristed.setId(1L);

        PersonDTO dto = imput.mockDTO(1);

        when(repositories.save(person)).thenReturn(peristed);

        var result = services.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("POST")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                ));

        assertEquals("First Name Test1", result.getFirstname());
        assertEquals("Last Name Test1", result.getLastname());
        assertEquals("Female", result.getGender());
        assertEquals("Address Test1", result.getAdress());
    }

    @Test
    void TestCreateWhitNullPerson() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                   services.create(null);
                });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualomMessage = exception.getMessage();

        assertTrue(actualomMessage.contains(expectedMessage));

    }

    @Test
    void update() {

        Person person = imput.mockEntity(1);
        Person peristed = person;
        peristed.setId(1L);

        PersonDTO dto = imput.mockDTO(1);

        when(repositories.findById(1L)).thenReturn(Optional.of(person));
        when(repositories.save(person)).thenReturn(peristed);

        var result = services.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("POST")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                ));

        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                ));

        assertEquals("First Name Test1", result.getFirstname());
        assertEquals("Last Name Test1", result.getLastname());
        assertEquals("Female", result.getGender());
        assertEquals("Address Test1", result.getAdress());
    }
    @Test
    void TestUpdateWhitNullPerson() {

        Exception exception = assertThrows(RequiredObjectIsNullException.class,
                () -> {
                    services.update(null);
                });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualomMessage = exception.getMessage();

        assertTrue(actualomMessage.contains(expectedMessage));

    }

    @Test
    void delete() {

        Person person = imput.mockEntity(1);
        person.setId(1L);
        when(repositories.findById(1L)).thenReturn(Optional.of(person));

        services.delete(1L);

        verify(repositories, times(1)).findById(anyLong());
        verify(repositories, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repositories);
    }

    @Test
    void findAll() {
        List<Person> list = imput.mockEntityList();
        when(repositories.findAll()).thenReturn(list);
        List<PersonDTO> people = services.findAll();

        assertNotNull(people);
        assertEquals(14,people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getId());
        assertNotNull(personOne.getLinks());

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("POST")
                ));

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                ));

        assertNotNull(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/person/v1/1")
                        && link.getType().equals("DELETE")
                ));

        assertEquals("First Name Test1", personOne.getFirstname());
        assertEquals("Last Name Test1", personOne.getLastname());
        assertEquals("Female", personOne.getGender());
        assertEquals("Address Test1", personOne.getAdress());


    var personFour = people.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getId());
        assertNotNull(personFour.getLinks());

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/person/v1/4")
                        && link.getType().equals("GET")
                ));

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/person/v1/4")
                        && link.getType().equals("POST")
                ));

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/person/v1")
                        && link.getType().equals("PUT")
                ));

        assertNotNull(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/person/v1/4")
                        && link.getType().equals("DELETE")
                ));

        assertEquals("First Name Test4", personFour.getFirstname());
        assertEquals("Last Name Test4", personFour.getLastname());
        assertEquals("Male", personFour.getGender());
        assertEquals("Address Test4", personFour.getAdress());

    }


}
