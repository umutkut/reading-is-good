package com.umutku.readingisgood.application;

import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.dto.BookDTO;
import com.umutku.readingisgood.response.RestResponse;

public interface BookService {
    RestResponse<Book> createBook(BookDTO bookDTO);

    RestResponse<Book> getBook(long id);

    RestResponse<Book> updateStock(long id, int newStock);
}
