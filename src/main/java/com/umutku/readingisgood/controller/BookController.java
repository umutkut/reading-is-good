package com.umutku.readingisgood.controller;

import com.umutku.readingisgood.application.BookService;
import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.dto.BookDTO;
import com.umutku.readingisgood.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<RestResponse<Book>> createBook(@Validated @RequestBody BookDTO bookDTO) {
        log.info("CreteBook Requested with {}", bookDTO);
        return ResponseEntity.ok(bookService.createBook(bookDTO));
    }

    @PutMapping(value = "/{bookId}/stock")
    public ResponseEntity<RestResponse<Book>> updateStock(
            @PathVariable("bookId") long bookId,
            @RequestParam("newStock") int newStock) {
        log.info("UpdateStock Requested with bookID {}, newStock {}", bookId, newStock);
        return ResponseEntity.ok(bookService.updateStock(bookId, newStock));
    }

    @GetMapping
    public ResponseEntity<RestResponse<Book>> getBook(@RequestParam long id) {
        log.info("GetBook Requested with id {}", id);
        return ResponseEntity.ok(bookService.getBook(id));
    }
}
