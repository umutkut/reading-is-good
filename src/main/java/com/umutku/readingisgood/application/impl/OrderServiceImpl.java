package com.umutku.readingisgood.application.impl;

import com.umutku.readingisgood.application.OrderService;
import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.domain.Order;
import com.umutku.readingisgood.domain.service.PlaceOrderDomainService;
import com.umutku.readingisgood.dto.OrderDTO;
import com.umutku.readingisgood.dto.request.DateIntervalDTO;
import com.umutku.readingisgood.exception.BookNotFoundException;
import com.umutku.readingisgood.exception.CustomerNotFoundException;
import com.umutku.readingisgood.exception.OrderNotFoundException;
import com.umutku.readingisgood.infrastructure.BookRepository;
import com.umutku.readingisgood.infrastructure.CustomerRepository;
import com.umutku.readingisgood.infrastructure.OrderRepository;
import com.umutku.readingisgood.response.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    private final PlaceOrderDomainService placeOrderDomainService;


    @Override
    public synchronized RestResponse<Order> place(OrderDTO orderDTO) {
        var customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException(orderDTO.getCustomerId()));

        var bookList = new ArrayList<Book>();
        for (long bookId : orderDTO.getBooks()) {
            var book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new BookNotFoundException(bookId));
            bookList.add(book);
        }

        var order = placeOrderDomainService.placeOrder(customer, bookList);
        var result = orderRepository.save(order);

        bookRepository.saveAll(bookList);
        customerRepository.save(customer);

        return new RestResponse<>(HttpStatus.CREATED, result);
    }

    @Override
    public RestResponse<Order> getOrder(long orderId) {
        var result = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        return new RestResponse<>(HttpStatus.OK, result);
    }

    @Override
    public RestResponse<List<Order>> getOrderByDate(DateIntervalDTO dateIntervalDTO) {
        var result = orderRepository.findAllByDateAfterAndDateBefore(dateIntervalDTO.getStartDate(), dateIntervalDTO.getEndDate());
        return new RestResponse<>(HttpStatus.OK, result);
    }


}
