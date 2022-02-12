package com.umutku.readingisgood.controller;

import com.umutku.readingisgood.application.CustomerService;
import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.dto.CustomerDTO;
import com.umutku.readingisgood.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/register")
    public ResponseEntity<RestResponse<Customer>> register(@Validated @RequestBody CustomerDTO customerDTO) {
        log.info("Register Requested with {}", customerDTO);
        return ResponseEntity.ok(customerService.register(customerDTO));
    }


}
