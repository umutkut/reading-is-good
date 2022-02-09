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

    @PutMapping(value="/{bookId}/stock")
    public ResponseEntity<RestResponse<Book>> updateStock(
            @PathVariable("bookId") long bookId,
            @RequestParam("newStock") int newStock) {
        return ResponseEntity.ok(bookService.updateStock(bookId, newStock));
    }

    @GetMapping
    public ResponseEntity<RestResponse<Book>> getBook(@RequestParam long id){
        return ResponseEntity.ok(bookService.getBook(id));
    }
}
