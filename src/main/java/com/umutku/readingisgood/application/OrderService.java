package com.umutku.readingisgood.application;

import com.umutku.readingisgood.domain.Order;
import com.umutku.readingisgood.dto.OrderDTO;
import com.umutku.readingisgood.response.RestResponse;

public interface OrderService {

    RestResponse<Order> place(OrderDTO orderDTO);

    RestResponse<Order> getOrder(long orderId);
}
