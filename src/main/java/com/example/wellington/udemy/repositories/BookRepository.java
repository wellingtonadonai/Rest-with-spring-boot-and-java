package com.example.wellington.udemy.repositories;

import com.example.wellington.udemy.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
