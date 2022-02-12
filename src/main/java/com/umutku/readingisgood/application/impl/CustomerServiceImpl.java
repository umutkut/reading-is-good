package com.umutku.readingisgood.application.impl;

import com.umutku.readingisgood.application.CustomerService;
import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.dto.CustomerDTO;
import com.umutku.readingisgood.dto.response.CustomerOrdersDTO;
import com.umutku.readingisgood.exception.CustomerNotFoundException;
import com.umutku.readingisgood.exception.InvalidUserNameException;
import com.umutku.readingisgood.infrastructure.CustomerRepository;
import com.umutku.readingisgood.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    public static final String USER_NAME_IN_USE = "USER NAME IN USE.";
    private final CustomerRepository customerRepository;

    @Override
    public RestResponse<Customer> register(CustomerDTO customerDTO) {
        customerRepository.findCustomerByUserName(customerDTO.getUsername())
                .ifPresent(customer -> {
                    throw new InvalidUserNameException(customer.getUserName(), USER_NAME_IN_USE);
                });

        Customer customer = Customer.fromDTO(customerDTO);
        var result = customerRepository.save(customer);

        return new RestResponse<>(HttpStatus.CREATED, result);
    }

    @Override
    public RestResponse<CustomerOrdersDTO> getOrders(long customerId) {
        var customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));

        var result = customer.getCustomerOrdersDTO();

        return new RestResponse<>(HttpStatus.OK, result);
    }


}
