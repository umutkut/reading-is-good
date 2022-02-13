package com.umutku.readingisgood.application;

import com.umutku.readingisgood.response.RestResponse;

public interface StatisticService {

    RestResponse<Integer> getTotalOrderCount(long customerId);

}
