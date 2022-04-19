package com.example.maktabdarsliklariapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //bitta fayl bitta byte array
    @OneToOne
    Attachment attachment;

    private byte[] bytes;
}
