package com.umutku.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@ToString
public class BookDTO extends BaseDTO {
    @NotNull
    private String title;


    private String author;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private double price;

    @NotNull
    private int stock;
}
