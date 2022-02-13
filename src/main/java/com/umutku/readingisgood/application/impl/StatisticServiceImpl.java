package com.umutku.readingisgood.application.impl;

import com.umutku.readingisgood.application.StatisticService;
import com.umutku.readingisgood.exception.CustomerNotFoundException;
import com.umutku.readingisgood.infrastructure.CustomerRepository;
import com.umutku.readingisgood.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final CustomerRepository customerRepository;

    @Override
    public RestResponse<Integer> getTotalOrderCount(long customerId) {
        var customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        int result = customer.getOrderCount();
        return new RestResponse<>(HttpStatus.OK, result);
    }
}
