package com.umutku.readingisgood.domain.service.impl;

import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.domain.Customer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOrderDomainServiceImplTest {

    @Test
    void placeOrder() {
        //Given
        Customer customer = new Customer("name", "password", "email", "address", new ArrayList<>());

        int stock1 = 10;
        Book book1 = new Book("title1", "author1", stock1);
        int stock2 = 75;
        Book book2 = new Book("title2", "author2", stock2);
        List<Book> books = List.of(book1, book2);

        //Method to test
        var placeOrderService = new PlaceOrderDomainServiceImpl();

        var order = placeOrderService.placeOrder(customer, books);

        //Assertions
        assertEquals(order.getCustomerId(), customer.getId());
        assertIterableEquals(order.getBooks(), books);
        assertEquals(stock1 - 1, order.getBooks().get(0).getStock());
        assertEquals(stock2 - 1, order.getBooks().get(1).getStock());
        assertTrue(customer.getOrders().contains(order));

    }
}