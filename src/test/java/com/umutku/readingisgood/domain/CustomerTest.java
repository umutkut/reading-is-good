package com.umutku.readingisgood.domain;

import com.umutku.readingisgood.dto.CustomerDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CustomerTest {
    String username = "username";
    String password = "password";
    String email = "email";
    String address = "address";
    CustomerDTO dto = new CustomerDTO(username, password, email, address);
    Customer customer = Customer.fromDTO(dto);

    @Test
    void fromDTO() {
        assertEquals(username, customer.getUserName());
        assertEquals(password, customer.getPassword());
        assertEquals(email, customer.getEmail());
        assertEquals(address, customer.getAddress());
    }

    @Test
    void addOrder() {
        customer.addOrder(new Order());
        assertFalse(customer.getOrders().isEmpty());
    }
}