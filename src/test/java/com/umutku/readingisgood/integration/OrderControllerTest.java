package com.umutku.readingisgood.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umutku.readingisgood.ReadingIsGoodApplication;
import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.domain.Order;
import com.umutku.readingisgood.dto.BookDTO;
import com.umutku.readingisgood.dto.CustomerDTO;
import com.umutku.readingisgood.dto.OrderDTO;
import com.umutku.readingisgood.dto.request.DateIntervalDTO;
import com.umutku.readingisgood.infrastructure.BookRepository;
import com.umutku.readingisgood.infrastructure.CustomerRepository;
import com.umutku.readingisgood.infrastructure.OrderRepository;
import com.umutku.readingisgood.util.TestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @MockBean
    OrderRepository orderRepository;
    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    BookRepository bookRepository;


    @LocalServerPort
    private int port;

    private BookDTO bookDTO;
    private Book book;
    private Book book2;

    private CustomerDTO customerDTO;
    private Customer customer;

    @BeforeEach
    void prepare() {
        String title = "TestTitle";
        String author = "TestAuthor";
        int stock = 10;
        bookDTO = new BookDTO(title, author, 52.8, stock);
        book = Book.fromDTO(bookDTO);
        book.setId(0);
        book2 = Book.fromDTO(bookDTO);
        book2.setId(1);

        String username = "UserName";
        String password = "Password";
        customerDTO = new CustomerDTO(username, password, null, null);
        customer = Customer.fromDTO(customerDTO);
    }

    @Test
    void testPlaceOrderHappyPath() throws JSONException, JsonProcessingException {
        //Given
        OrderDTO orderDTO = new OrderDTO(customer.getId(), List.of(book.getId(), book2.getId()));
        Order order = new Order(customer, List.of(book, book2));

        //Mock repository
        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.findById(book2.getId())).thenReturn(Optional.of(book2));
        Mockito.when(orderRepository.save(order)).thenReturn(order);

        //Call endpoint
        HttpEntity<OrderDTO> entity = new HttpEntity<>(orderDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                TestUtils.createURLWithPort("/api/v1/orders", port),
                HttpMethod.POST, entity, String.class);

        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        assertEquals("CREATED", responseObj.get("status"));

        Order responseOrder = new ObjectMapper().readValue(responseObj.get("data").toString(), Order.class);
        assertIterableEquals(order.getBooks(), responseOrder.getBooks());
        assertEquals(order, responseOrder);
    }

    @Test
    void testGetOrderHappyPath() throws JSONException, JsonProcessingException {
        //Given
        Order order = new Order(customer, List.of(book, book2));

        //Mock repository
        Mockito.when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        //Call endpoint
        HttpEntity<OrderDTO> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                TestUtils.createURLWithPort("/api/v1/orders?orderId=" + order.getId(), port),
                HttpMethod.GET, entity, String.class);

        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        assertEquals("OK", responseObj.get("status"));

        Order responseOrder = new ObjectMapper().readValue(responseObj.get("data").toString(), Order.class);
        assertEquals(order, responseOrder);
    }
    @Test
    void testGetOrderByTimeIntervalHappyPath() throws JSONException, JsonProcessingException {
        //Given
        Order order = new Order(customer, List.of(book, book2));
        Order order2 = new Order(customer, List.of(book, book2));
        List<Order> orders = List.of(order, order2);

        //FIXME: Fails beacuse can't convert dto into entity correctly
        DateIntervalDTO dateIntervalDTO = new DateIntervalDTO(Date.from(Instant.now()), Date.from(Instant.now()));

        //Mock repository
        Mockito.when(orderRepository.findAllByDateAfterAndDateBefore(Mockito.any(), Mockito.any())).thenReturn(orders);

        //Call endpoint
        HttpEntity<DateIntervalDTO> entity = new HttpEntity<>(dateIntervalDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                TestUtils.createURLWithPort("/api/v1/orders/byDate", port),
                HttpMethod.GET, entity, String.class);
        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        assertEquals("OK", responseObj.get("status"));

        List<Order> responseOrders = new ObjectMapper().readValue(responseObj.get("data").toString(), List.class);
        assertIterableEquals(orders, responseOrders);
    }

    @Test
    void testGetOrderNotFound() throws JSONException {

        //Mock repository
        Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        //Call endpoint
        HttpEntity<OrderDTO> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                TestUtils.createURLWithPort("/api/v1/orders?orderId=" + 0, port),
                HttpMethod.GET, entity, String.class);

        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        assertEquals("NOT_FOUND", responseObj.get("status"));

    }

    @Test
    void testNotEnoughStock() throws JSONException {
        //Given
        book.decreaseStock(10);
        OrderDTO orderDTO = new OrderDTO(customer.getId(), List.of(book.getId(), book2.getId()));
        Order order = new Order(customer, List.of(book, book2));

        //Mock repository
        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.findById(book2.getId())).thenReturn(Optional.of(book2));
        Mockito.when(orderRepository.save(order)).thenReturn(order);

        //Call endpoint
        HttpEntity<OrderDTO> entity = new HttpEntity<>(orderDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                TestUtils.createURLWithPort("/api/v1/orders", port),
                HttpMethod.POST, entity, String.class);
        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        Assertions.assertEquals("NOT_ACCEPTABLE", responseObj.get("status"));
    }

}
