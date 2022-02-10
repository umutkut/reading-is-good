package com.umutku.readingisgood.domain.service.impl;

import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.domain.Order;
import com.umutku.readingisgood.domain.service.PlaceOrderDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceOrderDomainServiceImpl implements PlaceOrderDomainService {

    @Override
    public Order placeOrder(Customer customer, List<Book> bookList) {

        Order order = new Order(customer.getId(), bookList);

        customer.addOrder(order);

        for (Book book : bookList) {
            book.decreaseStock(1);
        }

        return order;
    }
}
