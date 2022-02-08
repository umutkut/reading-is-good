package com.umutku.readingisgood.domain;

import com.umutku.readingisgood.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "book")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {

    private String title;

    private String author;

    @Column(name = "release_date")
    @Nullable
    private Date releaseDate;

    public static Book fromDTO(BookDTO bookDTO) {
        return new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getReleaseDate());
    }
}
