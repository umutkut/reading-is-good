package com.umutku.readingisgood.controller;

import com.umutku.readingisgood.application.BookService;
import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.dto.BookDTO;
import com.umutku.readingisgood.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<RestResponse<Book>> createBook(@Validated @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.createBook(bookDTO));
    }

    @PutMapping
    public ResponseEntity<RestResponse<Book>> updateBook(@RequestParam long id, @Validated @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @GetMapping
    public ResponseEntity<RestResponse<Book>> getBook(@RequestParam long id){
        return ResponseEntity.ok(bookService.getBook(id));
    }
}
