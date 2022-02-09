package com.umutku.readingisgood.domain;

import com.google.common.base.Preconditions;
import com.umutku.readingisgood.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "book")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {

    public static final String NOT_ENOUGH_STOCK = "NOT ENOUGH STOCK FOR BOOK: %d";

    private String title;

    private String author;

    private int stock;

    public static Book fromDTO(BookDTO bookDTO) {
        return new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getStock());
    }

    public void decreaseStock(int amount) {
        Preconditions.checkArgument(stock - amount >= 0, String.format(NOT_ENOUGH_STOCK, getId()));
        this.stock -= amount;
    }

    public void updateStock(int newStock) {
        this.stock = newStock;
    }
}
