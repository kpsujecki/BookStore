package com.sujecki.bookstore.service;

import com.sujecki.bookstore.model.Book;
import com.sujecki.bookstore.model.BookDTO;
import com.sujecki.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookServiceTest {

    @InjectMocks
    BookService bookService;
    @Mock
    BookRepository bookRepository;


    @BeforeAll
    public void setup(){
        MockitoAnnotations.openMocks(this);

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test name");
        book.setIsbn("123456");
        book.setSold(97);
        book.setTotalCount(321);

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
    void bookServiceShouldCorrectlyDidPurchaseOperation() {
        Book secondBook = new Book();
        secondBook.setId(2L);
        secondBook.setTitle("Test name");
        secondBook.setIsbn("123456");
        secondBook.setSold(97);
        secondBook.setTotalCount(321);

        when(bookRepository.getReferenceById(2L)).thenReturn(secondBook);
        bookService.updateStatusBookAfterPurchase(2L);

        Book book = bookRepository.getReferenceById(2L);

        assertThat(book.getTotalCount()).isEqualTo(320);
        assertThat(book.getSold()).isEqualTo(98);
    }

    @Test
    void bookServiceShouldRemovedCorrectlyBook() {
        bookService.deleteBook(1L);

        assertThat(bookRepository.findById(1L).isEmpty());

    }
}