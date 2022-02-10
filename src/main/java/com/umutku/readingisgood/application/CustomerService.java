package com.umutku.readingisgood.application;

import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.dto.BookDTO;
import com.umutku.readingisgood.dto.CustomerDTO;
import com.umutku.readingisgood.response.RestResponse;

public interface CustomerService {
    RestResponse<Customer> register(CustomerDTO customerDTO);

}
