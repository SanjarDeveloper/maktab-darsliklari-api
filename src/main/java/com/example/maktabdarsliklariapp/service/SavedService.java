package com.example.maktabdarsliklariapp.service;

import com.example.maktabdarsliklariapp.DTO.SavedDTO;
import com.example.maktabdarsliklariapp.entity.ApiResponse;
import com.example.maktabdarsliklariapp.entity.Book;
import com.example.maktabdarsliklariapp.entity.User;
import com.example.maktabdarsliklariapp.entity.enums.BookClass;
import com.example.maktabdarsliklariapp.entity.enums.BookLanguage;
import com.example.maktabdarsliklariapp.repository.BookRepository;
import com.example.maktabdarsliklariapp.repository.SavedRepository;
import com.example.maktabdarsliklariapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.maktabdarsliklariapp.entity.enums.BookClass.values;

@Service
@RequiredArgsConstructor
public class SavedService {
    private final SavedRepository savedRepository;
    private final BookRepository bookRepository;
    private final UsersRepository usersRepository;

    public ApiResponse getAllSaved() {
        Optional<User> byUsername = usersRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (byUsername.isEmpty()){
            return new ApiResponse("No such user :(",false);
        }
        else {
            User user = byUsername.get();
            List<Book> savedBooks = user.getSavedBooks();
            if (savedBooks.isEmpty()){
                return new ApiResponse("Empty!",true);
            }
            else {
                return new ApiResponse("Saved Books List:",true,savedBooks);
            }
        }
    }

    public ApiResponse getOneSaved(String class_no, String lang, String book_name) {
        int class_n = 0;
        for (BookClass value : values()) {
            if (class_no.equals(value.name())){
                class_n = value.ordinal();
            }
        }
        int lang_n = 0;
        for (BookLanguage value : BookLanguage.values()) {
            if (lang.equals(value.name())){
                lang_n = value.ordinal();
            }
        }
        Optional<Book> byClass_noAndBook_languageAndName = savedRepository.findOneBook(lang_n,class_n,book_name);
        if (byClass_noAndBook_languageAndName.isEmpty()){
            return new ApiResponse("No such saved book",false);
        }
        else return new ApiResponse("Here",true, byClass_noAndBook_languageAndName.get());
    }

    public ApiResponse addSaved(SavedDTO savedDTO) {
        int class_n = 0;
        for (BookClass value : values()) {
            if (savedDTO.getClass_no().equals(value.name())){
                class_n = value.ordinal();
            }
        }
        int lang_n = 0;
        for (BookLanguage value : BookLanguage.values()) {
            if (savedDTO.getLanguage().equals(value.name())){
                lang_n = value.ordinal();
            }
        }
        Optional<Book> oneBook = bookRepository.findOneBook(lang_n, class_n, savedDTO.getName());
        if (oneBook.isEmpty()){
            return new ApiResponse("No such book :(",false);
        }
        else {
            Optional<User> byUsername = usersRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            if(byUsername.isEmpty()){
                return new ApiResponse("No such user :(", false);
            }else {
                User user = byUsername.get();
                List<Book> savedBooks = user.getSavedBooks();
                savedBooks.add(oneBook.get());
                user.setSavedBooks(savedBooks);
                usersRepository.save(user);
                return new ApiResponse("Added",true);
            }
        }
    }

    public ApiResponse deleteSaved(String class_no, String lang, String book_name) {
        int class_n = 0;
        for (BookClass value : values()) {
            if (class_no.equals(value.name())){
                class_n = value.ordinal();
            }
        }
        int lang_n = 0;
        for (BookLanguage value : BookLanguage.values()) {
            if (lang.equals(value.name())){
                lang_n = value.ordinal();
            }
        }
        Optional<Book> oneBook = bookRepository.findOneBook(lang_n, class_n, book_name);
        if (oneBook.isEmpty()){
            return new ApiResponse("No such book :(",false);
        }
        else {
            Optional<User> byUsername = usersRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            if(byUsername.isEmpty()){
                return new ApiResponse("No such user :(", false);
            }else {
                User user = byUsername.get();
                List<Book> savedBooks = user.getSavedBooks();
                savedBooks.remove(oneBook.get());
                user.setSavedBooks(savedBooks);
                usersRepository.save(user);
                return new ApiResponse("Deleted",true);
            }
        }
    }

}
