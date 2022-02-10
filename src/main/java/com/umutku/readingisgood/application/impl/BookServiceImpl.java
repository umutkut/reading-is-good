package com.umutku.readingisgood.application.impl;

import com.umutku.readingisgood.application.BookService;
import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.dto.BookDTO;
import com.umutku.readingisgood.exception.BookNotFoundException;
import com.umutku.readingisgood.infrastructure.BookRepository;
import com.umutku.readingisgood.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public RestResponse<Book> createBook(BookDTO bookDTO) {
        Book book = Book.fromDTO(bookDTO);

        var result = bookRepository.save(book);

        return new RestResponse<>(HttpStatus.CREATED, result);
    }

    @Override
    public RestResponse<Book> getBook(long id) {
        var result = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return new RestResponse<>(HttpStatus.OK, result);
    }

    @Override
    public RestResponse<Book> updateStock(long id, int newStock) {
        var bookToUpdate = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        bookToUpdate.updateStock(newStock);

        var result = bookRepository.save(bookToUpdate);

        return new RestResponse<>(HttpStatus.OK, result);
    }
}
