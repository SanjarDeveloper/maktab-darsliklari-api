package com.example.maktabdarsliklariapp.repository;

import com.example.maktabdarsliklariapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SavedRepository extends JpaRepository<Book,Integer> {
    @Query(value = "select * from users_saved_books u where u.book_language = ?1 and u.class_no = ?2 and u.active = true", nativeQuery = true)
    List<Book> findAllBooks(Integer book_language, Integer class_no);


    @Query(value = "select * from users_saved_books u where u.book_language = ?1 and u.class_no = ?2 and u.name = ?3 and u.active = true", nativeQuery = true)
    Optional<Book> findOneBook(Integer book_language, Integer class_no, String book_name);

}
