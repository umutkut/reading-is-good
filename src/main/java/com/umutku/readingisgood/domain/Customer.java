package com.umutku.readingisgood.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umutku.readingisgood.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "customer")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

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

}
