package com.umutku.readingisgood.domain;

import com.umutku.readingisgood.dto.response.StatisticsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    Month month;
    Integer totalOrderCount;
    Integer totalBookCount;
    Double totalPurchasedAmount;

    public void addToTotalOrderCount(Integer addition){
        totalOrderCount += addition;
    }

    public void addToTotalBookCount(Integer addition){
        totalBookCount += addition;
    }

    public void addToTotalPurchasedAmount(Double addition){
        totalPurchasedAmount += addition;
    }

    public StatisticsDTO toDTO(){
        return new StatisticsDTO(month, totalOrderCount, totalBookCount, totalPurchasedAmount);
    }
}
