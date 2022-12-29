package com.sujecki.bookstore.service;

import com.sujecki.bookstore.model.Book;
import com.sujecki.bookstore.model.BookDTO;
import com.sujecki.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeAll
    public void setup(){
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test name");
        book.setIsbn("123456");

        bookRepository.save(book);
    }

    @Test
    void bookServiceShouldAddedCorrectlyBook() {
        BookDTO book = new BookDTO();
        book.setAuthor("JanKowalski");
        book.setTitle("NewBook");

        Book newBook = bookService.addNewBook(book);

        assertThat(bookRepository.findAll().contains(newBook));

    }

    @Test
    void bookServiceShouldUpdatedCorrectlyBook() {
        BookDTO book = new BookDTO();
        book.setTitle("UpdatedNameBook");
        book.setIsbn("19970705");

        Book newBook = bookService.updateBook(book, 1L);

        assertThat(newBook.getTitle().equals(book.getTitle()));
        assertThat(newBook.getIsbn().equals(book.getIsbn()));
    }

    @Test
    void bookServiceShouldRemovedCorrectlyBook() {
        bookService.deleteBook(1L);

        assertThat(bookRepository.findById(1L).isEmpty());

    }
}