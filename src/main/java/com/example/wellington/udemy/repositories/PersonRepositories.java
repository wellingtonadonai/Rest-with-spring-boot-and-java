package com.example.wellington.udemy.repositories;

import com.example.wellington.udemy.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepositories extends JpaRepository <Person, Long> {
}
