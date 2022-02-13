package com.umutku.readingisgood.controller;

import com.umutku.readingisgood.application.StatisticService;
import com.umutku.readingisgood.dto.response.StatisticsDTO;
import com.umutku.readingisgood.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@Slf4j
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping(value = "/{customerId}/totalOrderCount")
    public ResponseEntity<RestResponse<Integer>> getTotalOrderCount(@PathVariable("customerId") long customerId) {
        log.info("Total Order Count Requested for customer {}", customerId);
        return ResponseEntity.ok(statisticService.getTotalOrderCount(customerId));
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<RestResponse<Map<Integer,StatisticsDTO>>> getStatistics(@PathVariable("customerId") long customerId) {
        log.info("Statistics Requested for customer {}", customerId);
        return ResponseEntity.ok(statisticService.getStatistics(customerId));
    }
}
