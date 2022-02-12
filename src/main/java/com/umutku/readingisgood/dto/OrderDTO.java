package com.umutku.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class OrderDTO extends BaseDTO {
    @NotNull
    private long customerId;
    @NotNull
    private List<Long> books;
}
