package com.umutku.readingisgood.controller;

import com.umutku.readingisgood.application.CustomerService;
import com.umutku.readingisgood.application.OrderService;
import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.domain.Order;
import com.umutku.readingisgood.dto.CustomerDTO;
import com.umutku.readingisgood.dto.OrderDTO;
import com.umutku.readingisgood.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<RestResponse<Order>> place(@Validated @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.place(orderDTO));
    }


}
