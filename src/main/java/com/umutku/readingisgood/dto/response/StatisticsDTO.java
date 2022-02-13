package com.umutku.readingisgood.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Month;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDTO {
    Month month;
    Integer totalOrderCount;
    Integer totalBookCount;
    Double totalPurchasedAmount;
}
