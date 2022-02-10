package com.umutku.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO extends BaseDTO {
    @NotNull
    private long customerId;
    @NotNull
    private List<Long> books;
}
