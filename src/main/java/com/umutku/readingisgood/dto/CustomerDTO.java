package com.umutku.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CustomerDTO extends BaseDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;

    private String email;

    private String address;
}
