package com.umutku.readingisgood.controller;

import com.umutku.readingisgood.application.OrderService;
import com.umutku.readingisgood.domain.Order;
import com.umutku.readingisgood.dto.OrderDTO;
import com.umutku.readingisgood.dto.request.DateIntervalDTO;
import com.umutku.readingisgood.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<RestResponse<Order>> place(@Validated @RequestBody OrderDTO orderDTO) {
        log.info("OrderPlace Requested with {}", orderDTO);
        return ResponseEntity.ok(orderService.place(orderDTO));
    }

    @GetMapping(value = "/byDate")
    public ResponseEntity<RestResponse<List<Order>>> getOrderByDate(@Validated @RequestBody DateIntervalDTO dateIntervalDTO) {
        log.info("GetOrder Requested with interval from {} to {}", dateIntervalDTO.getStartDate(), dateIntervalDTO.getEndDate());
        return ResponseEntity.ok(orderService.getOrderByDate(dateIntervalDTO));
    }

    @GetMapping
    public ResponseEntity<RestResponse<Order>> getOrder(@RequestParam long orderId) {
        log.info("GetOrder Requested with {}", orderId);
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

}
