package com.umutku.readingisgood.domain.service;

import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.domain.Order;

import java.util.List;

public interface PlaceOrderDomainService {

    Order placeOrder(Customer customer, List<Book> bookList);

}
