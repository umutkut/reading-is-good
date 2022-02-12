package com.umutku.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
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
