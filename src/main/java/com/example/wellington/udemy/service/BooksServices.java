package com.example.wellington.udemy.service;


import com.example.wellington.udemy.data.dto.BooksDTO;
import com.example.wellington.udemy.exceptions.RequiredObjectIsNullException;
import com.example.wellington.udemy.exceptions.ResourceNotFoundException;
import com.example.wellington.udemy.model.Books;
import com.example.wellington.udemy.repositories.BooksRepositories;
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
public class BooksServices {
    private final AtomicLong couter = new AtomicLong();
    private Logger logger = Logger.getLogger(BooksServices.class.getName());

    @Autowired
    BooksRepositories repositories;





    public List<BooksDTO> findAll(){
        logger.info("Finding all Books");
        var bookss = parseListObject(repositories.findAll(), BooksDTO.class);
        bookss.forEach(this::addHateoasLinkdto);
        return bookss;


    }



    public BooksDTO findById(Long id){
        logger.info("Finding one Books");

         var entity = repositories.findById(id)
                 .orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
        var dto = parseObject(entity, BooksDTO.class);
        addHateoasLinkdto(dto);
        return dto;



    }

    public BooksDTO create(BooksDTO books){

        if (books == null) throw new RequiredObjectIsNullException();
        logger.info("create one Books");
        var entity = parseObject(books, Books.class);

        var dto = parseObject (repositories.save(entity),BooksDTO.class);
        addHateoasLinkdto(dto);
        return dto;

    }

    public BooksDTO update(BooksDTO books) {
        if (books == null) throw new RequiredObjectIsNullException();
        logger.info("update one Books");
        Books entity = repositories.findById(books.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        entity.setAuthor(books.());
        entity.setTitle(books.getLastname());
        entity.setAdress(books.getAdress());
        entity.setGender(books.getGender());

        var dto = parseObject(repositories.save(entity),BooksDTO.class);
        addHateoasLinkdto(dto);
        return dto;
    }

    public void delete (Long id) {
        logger.info("delete one Books");
        Books entity = repositories.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));

        repositories.delete(entity);

    }


    private void addHateoasLinkdto (BooksDTO dto) {
        dto.add(linkTo(methodOn(BooksController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BooksController.class).findAll()).withRel("findall").withType("GET"));
        dto.add(linkTo(methodOn(BooksController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BooksController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BooksController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
