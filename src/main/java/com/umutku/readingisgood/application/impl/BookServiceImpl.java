package com.umutku.readingisgood.application.impl;

import com.umutku.readingisgood.application.BookService;
import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.dto.BookDTO;
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
}
