package com.umutku.readingisgood.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umutku.readingisgood.dto.CustomerDTO;
import com.umutku.readingisgood.dto.OrderDTO;
import com.umutku.readingisgood.dto.response.CustomerOrdersDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Month;
import java.util.*;

@Getter
@Setter
@Table(name = "customer")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    private final Calendar calendar = Calendar.getInstance();


    //TODO: spring security will be integrated

    String userName;

    String password;

    @Nullable
    String email;

    @Nullable
    String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

    public static Customer fromDTO(CustomerDTO dto) {
        return new Customer(dto.getUsername(), dto.getPassword(), dto.getEmail(), dto.getAddress(), new ArrayList<>());
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    @JsonIgnore
    public CustomerOrdersDTO getCustomerOrdersDTO() {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            orderDTOS.add(order.toDTO());
        }
        return new CustomerOrdersDTO(getId(), orderDTOS);
    }

    @JsonIgnore
    public int getOrderCount() {
        return orders.size();
    }

    @JsonIgnore
    public Map<Integer, Statistics> getStatistics() {
        //TODO: should be seperated by years too

        Map<Integer, Statistics> statisticsMap = new HashMap<>();

        for (Order order : orders) {
            fillStatisticsMapWithOrder(statisticsMap, order);
        }

        return statisticsMap;
    }

    private void fillStatisticsMapWithOrder(Map<Integer, Statistics> statisticsMap, Order order) {

        var orderDate = order.getDate();
        calendar.setTime(orderDate);
        var month = calendar.get(Calendar.MONTH) + 1; //index conversion

        var statistic = statisticsMap.getOrDefault(month, new Statistics(Month.of(month), 0, 0, 0.0));

        statistic.addToTotalOrderCount(1);
        statistic.addToTotalBookCount(order.getBookCount());
        statistic.addToTotalPurchasedAmount(order.totalOrderAmount());

        statisticsMap.put(month, statistic);
    }
}
