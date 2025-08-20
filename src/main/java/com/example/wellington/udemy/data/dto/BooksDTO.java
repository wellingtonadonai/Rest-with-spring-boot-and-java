package com.example.wellington.udemy.data.dto;


import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;


public class BooksDTO extends RepresentationModel<BooksDTO> implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    private String author;
    private String launch_date;
    private Double price;
    private String title;

    public BooksDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(String launch_date) {
        this.launch_date = launch_date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BooksDTO books)) return false;
        return Objects.equals(getId(), books.getId()) && Objects.equals(getAuthor(), books.getAuthor()) && Objects.equals(getLaunch_date(), books.getLaunch_date()) && Objects.equals(getPrice(), books.getPrice()) && Objects.equals(getTitle(), books.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getLaunch_date(), getPrice(), getTitle());
    }
}