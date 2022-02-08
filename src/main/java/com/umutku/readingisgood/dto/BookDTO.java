package com.umutku.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
public class BookDTO extends BaseDTO {
    @NotNull
    private String title;

    @NotNull
    private String author;

    private Date releaseDate;
}
