package com.example.maktabdarsliklariapp.controller;

import com.example.maktabdarsliklariapp.DTO.SavedDTO;
import com.example.maktabdarsliklariapp.entity.ApiResponse;
import com.example.maktabdarsliklariapp.service.SavedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saved")
@RequiredArgsConstructor
public class SavedController {
    private final SavedService savedService;

    @PreAuthorize("hasAuthority('READ_ALL_SAVED')")
    @GetMapping("/all")
    public ResponseEntity getAllSaved(){
        ApiResponse allSaved = savedService.getAllSaved();
        return ResponseEntity.ok().body(allSaved);
    }
    @PreAuthorize("hasAuthority('READ_ONE_SAVED')")
    @GetMapping("/{class}/{lang}/{book}")
    public ResponseEntity getOneSaved(@PathVariable("class") String class_no, @PathVariable("lang") String lang, @PathVariable("book") String book_name){
        ApiResponse oneSaved = savedService.getOneSaved(class_no, lang, book_name);
        return ResponseEntity.ok().body(oneSaved);
    }
    @PreAuthorize("hasAuthority('CREATE_SAVED')")
    @PostMapping
    public ResponseEntity addSaved(@RequestBody SavedDTO savedDTO){
        ApiResponse apiResponse = savedService.addSaved(savedDTO);
        return ResponseEntity.ok().body(apiResponse);
    }

    @PreAuthorize("hasAuthority('DELETE_SAVED')")
    @DeleteMapping("/{class}/{lang}/{book}")
    public ResponseEntity deleteSaved(@PathVariable("class") String class_no, @PathVariable("lang") String lang, @PathVariable("book") String book_name){
        ApiResponse apiResponse = savedService.deleteSaved(class_no, lang, book_name);
        return ResponseEntity.ok().body(apiResponse);
    }
}
