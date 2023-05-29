package com.learning.devopscrudk8s.repository;

import com.learning.devopscrudk8s.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}