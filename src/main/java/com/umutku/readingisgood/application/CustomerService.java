package com.umutku.readingisgood.application;

import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.dto.CustomerDTO;
import com.umutku.readingisgood.dto.response.CustomerOrdersDTO;
import com.umutku.readingisgood.response.RestResponse;

public interface CustomerService {
    RestResponse<Customer> register(CustomerDTO customerDTO);

    RestResponse<CustomerOrdersDTO>  getOrders(long customerId);
}
