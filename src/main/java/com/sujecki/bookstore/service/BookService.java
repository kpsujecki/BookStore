package com.sujecki.bookstore.service;

import com.sujecki.bookstore.model.Book;
import com.sujecki.bookstore.model.BookDTO;
import com.sujecki.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getById(Long id){
        return bookRepository.findById(id);
    }
    public Book addNewBook(BookDTO book){
        Book newBook = modelMapper.map(book, Book.class);
        newBook = bookRepository.save(newBook);
        return newBook;
    }

    public Book updateBook(BookDTO book, Long id){
        Book newBook = modelMapper.map(book, Book.class);
        newBook.setId(id);
        bookRepository.save(newBook);
        return newBook;
    }

    public void deleteBook(Long id){
            Book bookToDelete = bookRepository.getReferenceById(id);
            bookRepository.delete(bookToDelete);
    }
}
