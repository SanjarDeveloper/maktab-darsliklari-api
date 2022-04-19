package com.example.maktabdarsliklariapp.repository;

import com.example.maktabdarsliklariapp.entity.Attachment;
import com.example.maktabdarsliklariapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    Optional<Attachment> findByBookName(String book_name);
}
