package com.sujecki.bookstore.controller;

import com.sujecki.bookstore.model.Book;
import com.sujecki.bookstore.model.BookDTO;
import com.sujecki.bookstore.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        Optional<Book> book = bookService.getById(id);

        if(book.isPresent()){
            return new ResponseEntity<>(book, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Not found book with this ID", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Optional<Book> book = bookService.getById(id);

        if(book.isPresent()){
            bookService.deleteBook(id);
            return new ResponseEntity<>("Book with ID {id} removed successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Not found book with this ID", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
        Optional<Book> book = bookService.getById(id);

        if(book.isPresent()){
            bookService.updateBook(bookDTO, id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Not found book with this ID", HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("buy/{id}")
    public ResponseEntity<?> buyBook(@PathVariable Long id) {
        Optional<Book> book = bookService.getById(id);

        if(book.isPresent()){
            if(book.get().getTotalCount()>0){
                bookService.updateStatusBookAfterPurchase(id);
                return new ResponseEntity<>("Book has been bought successfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Book is not available", HttpStatus.valueOf("NOT AVAILABLE"));
            }

        }else{
            return new ResponseEntity<>("Not found book with this ID", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewBook(@RequestBody BookDTO book){

        Book newBook = bookService.addNewBook(book);
        return ResponseEntity.ok(newBook);
    }


}
