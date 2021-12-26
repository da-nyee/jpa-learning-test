package com.example.jpalearningtest;

import com.example.jpalearningtest.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
