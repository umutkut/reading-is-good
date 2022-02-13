package com.umutku.readingisgood.application.impl;

import com.umutku.readingisgood.application.StatisticService;
import com.umutku.readingisgood.domain.Statistics;
import com.umutku.readingisgood.dto.response.StatisticsDTO;
import com.umutku.readingisgood.exception.CustomerNotFoundException;
import com.umutku.readingisgood.infrastructure.CustomerRepository;
import com.umutku.readingisgood.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public RestResponse<Map<Integer, StatisticsDTO>> getStatistics(long customerId) {
        var customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));

        var statisticsMap = customer.getStatistics();

        var result = convertStatisticsMapToDTOMap(statisticsMap);

        return new RestResponse<>(HttpStatus.OK, result);
    }

    private Map<Integer, StatisticsDTO> convertStatisticsMapToDTOMap(Map<Integer, Statistics> statisticsMap) {
        var dtoMap = new HashMap<Integer, StatisticsDTO>();
        var keySet = statisticsMap.keySet();
        for (Integer key : keySet) {
            dtoMap.put(key, statisticsMap.get(key).toDTO());
        }
        return dtoMap;
    }

}
