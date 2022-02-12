package com.umutku.readingisgood.dto.response;

import com.umutku.readingisgood.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrdersDTO {
    long customerId;
    List<OrderDTO> orders;
}
