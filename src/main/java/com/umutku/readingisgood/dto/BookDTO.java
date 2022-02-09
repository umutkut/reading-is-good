package com.umutku.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class BookDTO extends BaseDTO {
    @NotNull
    private String title;


    private String author;

    @NotNull
    private int stock;
}
