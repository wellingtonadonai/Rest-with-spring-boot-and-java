package com.example.wellington.udemy.service;

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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}