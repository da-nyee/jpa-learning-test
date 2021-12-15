package com.example.jpalearningtest.save;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.jpalearningtest.book.Book;
import com.example.jpalearningtest.book.BookRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class JpaLearningTest {

    @Autowired
    private BookRepository bookRepository;

    private int count;
    private List<Book> books;

    @BeforeEach
    void setUp() {
        count = 10_000;
        books = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            books.add(new Book("제목", "저자"));
        }
    }

    @DisplayName("save() - 10,000개의 책을 저장한다.")
    @Test
    void save_Books() {
        // when
        for (Book book : books) {
            bookRepository.save(book);
        }

        // then
        assertThat(bookRepository.count()).isEqualTo(count);
    }

    @DisplayName("saveAll() - 10,000개의 책을 저장한다.")
    @Test
    void saveAll_Books() {
        // when
        bookRepository.saveAll(books);

        // then
        assertThat(bookRepository.count()).isEqualTo(count);
    }
}
