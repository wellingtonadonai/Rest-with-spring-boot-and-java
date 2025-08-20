package com.example.wellington.udemy.repositories;

import com.example.wellington.udemy.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepositories extends JpaRepository<Books, Long> {
}
