package com.learning.devopscrudk8s.controller;

import com.learning.devopscrudk8s.entity.Book;
import com.learning.devopscrudk8s.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(final BookService service) {
        this.service = service;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return service.addBook(book);
    }

    @GetMapping
    public List<Book> getBooks(){
        return service.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id){
        return service.getBookById(id);
    }
}
