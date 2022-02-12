package com.umutku.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@ToString
public class BookDTO extends BaseDTO {
    @NotNull
    private String title;


    private String author;

    @NotNull
    private int stock;
}
