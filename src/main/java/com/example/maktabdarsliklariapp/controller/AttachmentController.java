//package com.example.maktabdarsliklariapp.controller;
//
//import com.example.maktabdarsliklariapp.entity.Attachment;
//import com.example.maktabdarsliklariapp.entity.AttachmentContent;
//import com.example.maktabdarsliklariapp.repository.AttachmentContentRepository;
//import com.example.maktabdarsliklariapp.repository.AttachmentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import java.io.IOException;
//import java.util.*;
//
////responsebody + bean
//@RestController
//@RequestMapping("/api/file")
//public class AttachmentController {
//
//    @Autowired
//    AttachmentRepository attachmentRepository;
//    @Autowired
//    AttachmentContentRepository attachmentContentRepository;
//
//    //db ga saqlash
//    @PostMapping("/upload")
//    public UUID saveFile(MultipartHttpServletRequest request) throws IOException {
//        MultipartFile file = request.getFile("ketmon");
//        Attachment save = null;
//        if (file != null || !file.isEmpty()) {
//            Attachment attachment = new Attachment();
//            attachment.setContentType(file.getContentType());
//            attachment.setName(file.getOriginalFilename());
//            attachment.setSize(file.getSize());
//            save = attachmentRepository.save(attachment);
//
//
//            AttachmentContent attachmentContent = new AttachmentContent();
//            attachmentContent.setBytes(file.getBytes());
//            attachmentContent.setAttachment(save);
//
//            attachmentContentRepository.save(attachmentContent);
//
//        }
//
//        return save.getId();
//    }
//
//    @PostMapping("/uploadMany")
//    public List<UUID> saveFileMany(MultipartHttpServletRequest request) throws IOException {
//        Iterator<String> fileNames = request.getFileNames(); //bir nechta nomli
//        List<UUID> uuids = new ArrayList<>();
//
//        while (fileNames.hasNext()) {
//            //keyingisi bormi?
//            String name = fileNames.next();
//            List<MultipartFile> files = request.getFiles(name);
//            for (MultipartFile file : files) {
//                Attachment save = null;
//                if (file != null || !file.isEmpty()) {
//                    Attachment attachment = new Attachment();
//                    attachment.setContentType(file.getContentType());
//                    attachment.setName(file.getOriginalFilename());
//                    attachment.setSize(file.getSize());
//                    save = attachmentRepository.save(attachment);
//
//
//                    AttachmentContent attachmentContent = new AttachmentContent();
//                    attachmentContent.setBytes(file.getBytes());
//                    attachmentContent.setAttachment(save);
//
//                    attachmentContentRepository.save(attachmentContent);
//                    uuids.add(save.getId());
//                }
//            }
//
//        }
//        return uuids;
////        List<MultipartFile> files = request.getFiles("ketmon");
//
//    }
//
//    @GetMapping("/{id}")
//    public HttpEntity<?> download(@PathVariable UUID id) {
//        Optional<Attachment> byId = attachmentRepository.findById(id);
//        Attachment attachment = byId.get();
//
//        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
//
//        AttachmentContent attachmentContent = optionalAttachmentContent.get();
////        attachmentContent.getBytes(); //faylni baytlari
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.valueOf(attachment.getContentType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "file")
//                .body(attachmentContent.getBytes());
//    }
//
//
//}
//
