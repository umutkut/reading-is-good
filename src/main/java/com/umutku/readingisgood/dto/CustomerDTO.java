package com.umutku.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@ToString
public class CustomerDTO extends BaseDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;

    private String email;

    private String address;
}
