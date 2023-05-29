package com.learning.devopscrudk8s.service;

import com.learning.devopscrudk8s.entity.Book;
import com.learning.devopscrudk8s.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(final BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(Book book){
        return repository.save(book);
    }

    public List<Book> getBooks(){
        return repository.findAll();
    }

    public Book getBookById(int id){
        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Book with id : " + id + "does not exist")
        );
    }

}
