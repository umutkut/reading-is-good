package com.umutku.readingisgood.application;

import com.umutku.readingisgood.dto.response.StatisticsDTO;
import com.umutku.readingisgood.response.RestResponse;

import java.util.Map;

public interface StatisticService {

    RestResponse<Integer> getTotalOrderCount(long customerId);

    RestResponse<Map<Integer, StatisticsDTO>> getStatistics(long customerId);

}
