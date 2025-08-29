package com.example.wellington.udemy.service;


import com.example.wellington.udemy.controllers.BookController;
import com.example.wellington.udemy.data.dto.BookDTO;
import com.example.wellington.udemy.exceptions.RequiredObjectIsNullException;
import com.example.wellington.udemy.exceptions.ResourceNotFoundException;
import com.example.wellington.udemy.model.Book;
import com.example.wellington.udemy.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import static com.example.wellington.udemy.mapper.ObjectMapper.parseListObject;
import static com.example.wellington.udemy.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
    private final AtomicLong couter = new AtomicLong();
    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repositories;





    public List<BookDTO> findAll(){
        logger.info("Finding all Books");
        var bookss = parseListObject(repositories.findAll(), BookDTO.class);
        bookss.forEach(this::addHateoasLinkdto);
        return bookss;


    }



    public BookDTO findById(Long id){
        logger.info("Finding one Books");

         var entity = repositories.findById(id)
                 .orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinkdto(dto);
        return dto;



    }

    public BookDTO create(BookDTO books){

        if (books == null) throw new RequiredObjectIsNullException();
        logger.info("create one Books");
        var entity = parseObject(books, Book.class);

        var dto = parseObject (repositories.save(entity), BookDTO.class);
        addHateoasLinkdto(dto);
        return dto;

    }

    public BookDTO update(BookDTO books) {
        if (books == null) throw new RequiredObjectIsNullException();
        logger.info("update one Books");
        Book entity = repositories.findById(books.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        entity.setAuthor(books.getAuthor());
        entity.setTitle(books.getTitle());
        entity.setLaunchdate(books.getLaunchdate());
        entity.setPrice(books.getPrice());

        var dto = parseObject(repositories.save(entity), BookDTO.class);
        addHateoasLinkdto(dto);
        return dto;
    }

    public void delete (Long id) {
        logger.info("delete one Books");
        Book entity = repositories.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));

        repositories.delete(entity);

    }


    private void addHateoasLinkdto (BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findall").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
