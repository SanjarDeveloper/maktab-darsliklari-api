package com.example.maktabdarsliklariapp.entity;

import com.example.maktabdarsliklariapp.entity.enums.BookLanguage;
import com.example.maktabdarsliklariapp.entity.enums.BookClass;
import lombok.*;

import javax.persistence.*;

@Entity(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated
    private BookClass class_no;
    @Enumerated
    private BookLanguage book_language;
    private String description;
    private String Author;
    private boolean active;
}
