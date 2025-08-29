package com.example.wellington.udemy.service;

import com.example.wellington.udemy.data.dto.BookDTO;
import com.example.wellington.udemy.exceptions.RequiredObjectIsNullException;

import com.example.wellington.udemy.model.Book;
import com.example.wellington.udemy.repositories.BookRepository;
import com.example.wellington.udemy.unitetest.mapper.mocks.MockBook;
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
class BookServicesTest {

    MockBook imput;

    @InjectMocks
    private BookServices services;

    @Mock
    BookRepository repositories;

    @BeforeEach
    void setUp() {
        imput = new MockBook();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void findById() {
        Book person = imput.mockEntity(1);
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

        assertEquals("Some Author1", result.getAuthor());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchdate());

    }

    @Test
    void create() {
        Book person = imput.mockEntity(1);
        Book peristed = person;
        peristed.setId(1L);

        BookDTO dto = imput.mockDTO(1);

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

        assertEquals("Some Author1", result.getAuthor());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchdate());
    }

    @Test
    void TestCreateWhitNullBook() {

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

        Book person = imput.mockEntity(1);
        Book peristed = person;
        peristed.setId(1L);

        BookDTO dto = imput.mockDTO(1);

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

        assertEquals("Some Author1", result.getAuthor());
        assertEquals(25D, result.getPrice());
        assertEquals("Some Title1", result.getTitle());
        assertNotNull(result.getLaunchdate());
    }
    @Test
    void TestUpdateWhitNullBook() {

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

        Book person = imput.mockEntity(1);
        person.setId(1L);
        when(repositories.findById(1L)).thenReturn(Optional.of(person));

        services.delete(1L);

        verify(repositories, times(1)).findById(anyLong());
        verify(repositories, times(1)).delete(any(Book.class));
        verifyNoMoreInteractions(repositories);
    }

    @Test
    void findAll() {
        List<Book> list = imput.mockEntityList();
        when(repositories.findAll()).thenReturn(list);
        List<BookDTO> books = services.findAll();

        assertNotNull(books);
        assertEquals(14,books.size());

        var bookOne = books.get(1);

        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getLinks());

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("POST")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")
                ));

        assertNotNull(bookOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/1")
                        && link.getType().equals("DELETE")
                ));

        assertEquals("Some Author1", bookOne.getAuthor());
        assertEquals(25D, bookOne.getPrice());
        assertEquals("Some Title1", bookOne.getTitle());
        assertNotNull(bookOne.getLaunchdate());


    var bookFour = books.get(4);

        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getLinks());

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self")
                        && link.getHref().endsWith("/api/book/v1/4")
                        && link.getType().equals("GET")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("GET")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create")
                        && link.getHref().endsWith("/api/book/v1/4")
                        && link.getType().equals("POST")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update")
                        && link.getHref().endsWith("/api/book/v1")
                        && link.getType().equals("PUT")
                ));

        assertNotNull(bookFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("delete")
                        && link.getHref().endsWith("/api/book/v1/4")
                        && link.getType().equals("DELETE")
                ));

        assertEquals("Some Author4", bookFour.getAuthor());
        assertEquals(25D, bookFour.getPrice());
        assertEquals("Some Title4", bookFour.getTitle());
        assertNotNull(bookFour.getLaunchdate());

    }


}
