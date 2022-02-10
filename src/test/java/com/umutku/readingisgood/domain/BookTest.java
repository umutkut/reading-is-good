package com.umutku.readingisgood.domain;

import com.umutku.readingisgood.dto.BookDTO;
import com.umutku.readingisgood.exception.NotEnoughStockException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookTest {

    @Test
    void fromDTO() {
        BookDTO dto = new BookDTO("title", "author", 11);
        Book book = Book.fromDTO(dto);

        assertEquals(dto.getAuthor(), book.getAuthor());
        assertEquals(dto.getTitle(), book.getTitle());
        assertEquals(dto.getStock(), book.getStock());
    }

    @Test
    void decreaseStock() {
        Book book = new Book("title", "author", 11);
        book.decreaseStock(5);
        assertEquals(6, book.getStock());
    }

    @Test
    void invalidDecreaseStock() {
        Book book = new Book("title", "author", 11);
        assertThrows(NotEnoughStockException.class, () -> book.decreaseStock(15));
    }

    @Test
    void updateStock() {
        Book book = new Book("title", "author", 11);
        int newStock = 111;
        book.updateStock(newStock);
        assertEquals(newStock, book.getStock());

    }
}