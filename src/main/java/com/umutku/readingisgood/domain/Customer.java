package com.umutku.readingisgood.domain;

import com.umutku.readingisgood.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "order")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    //TODO: spring security will be integrated

    @Column(unique = true)
    String userName;

    String password;

    @Nullable
    String email;

    @Nullable
    String address;

    @OneToMany
    @JoinColumn(name = "orders")
    @Lazy
    private List<Order> orders;

    public static Customer fromDTO(CustomerDTO dto) {
        return new Customer(dto.getUsername(), dto.getPassword(), dto.getEmail(), dto.getAddress(), new ArrayList<>());
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

}
