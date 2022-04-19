package com.example.maktabdarsliklariapp.DTO;

import com.example.maktabdarsliklariapp.entity.enums.BookClass;
import com.example.maktabdarsliklariapp.entity.enums.BookLanguage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {
    private String name;
    private String class_no;
    private String language;
    private String description;
    private String author;
    private boolean active;
}
