package com.umutku.readingisgood.application;

import com.umutku.readingisgood.domain.Order;
import com.umutku.readingisgood.dto.OrderDTO;
import com.umutku.readingisgood.dto.request.DateIntervalDTO;
import com.umutku.readingisgood.response.RestResponse;

import java.util.List;

public interface OrderService {

    RestResponse<Order> place(OrderDTO orderDTO);

    RestResponse<Order> getOrder(long orderId);

    RestResponse<List<Order>> getOrderByDate(DateIntervalDTO dateIntervalDTO);
}
