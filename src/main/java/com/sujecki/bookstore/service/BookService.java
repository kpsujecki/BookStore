package com.sujecki.bookstore.service;

import com.sujecki.bookstore.model.Book;
import com.sujecki.bookstore.model.BookDTO;
import com.sujecki.bookstore.model.Category;
import com.sujecki.bookstore.repository.BookRepository;
import com.sujecki.bookstore.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    private CategoryRepository categoryRepository;


    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    private ModelMapper modelMapper = new ModelMapper();

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> getAllBooksByCategory(Long id){
        List<Book> books = null;
        Category category = categoryRepository.getReferenceById(id);
        books = bookRepository.findAllByCategory(category);
        return books;
    }

    public List<Book> getAllBooksByAuthor(String author){
        List<Book> books = null;
        books = bookRepository.findAllByAuthor(author);
        return books;

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

    public void updateStatusBookAfterPurchase(Long id){
        Book bookToUpdate = bookRepository.getReferenceById(id);

        bookToUpdate.setTotalCount(bookToUpdate.getTotalCount()-1);
        bookToUpdate.setSold(bookToUpdate.getSold()+1);

        bookRepository.save(bookToUpdate);
    }

}
