package com.umutku.readingisgood.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umutku.readingisgood.ReadingIsGoodApplication;
import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.domain.Order;
import com.umutku.readingisgood.domain.service.PlaceOrderDomainService;
import com.umutku.readingisgood.dto.BookDTO;
import com.umutku.readingisgood.dto.CustomerDTO;
import com.umutku.readingisgood.dto.OrderDTO;
import com.umutku.readingisgood.infrastructure.BookRepository;
import com.umutku.readingisgood.infrastructure.CustomerRepository;
import com.umutku.readingisgood.infrastructure.OrderRepository;
import com.umutku.readingisgood.util.TestUtils;
import org.json.JSONException;
import org.json.JSONObject;
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
    @MockBean
    PlaceOrderDomainService placeOrderDomainService;

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
        bookDTO = new BookDTO(title, author, stock);
        book = Book.fromDTO(bookDTO);
        book2 = Book.fromDTO(bookDTO);

        String username = "UserName";
        String password = "Password";
        customerDTO = new CustomerDTO(username, password, null, null);
        customer = Customer.fromDTO(customerDTO);
    }

    @Test
    void testPlaceOrderHappyPath() throws JSONException, JsonProcessingException {
        //Given
        OrderDTO orderDTO = new OrderDTO(customer.getId(), List.of(book.getId(), book2.getId()));
        Order order = new Order(customer.getId(), List.of(book, book2));

        //Mock repository
        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.findById(book2.getId())).thenReturn(Optional.of(book2));
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        Mockito.when(placeOrderDomainService.placeOrder(Mockito.any(), Mockito.anyList())).thenReturn(order);

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
        assertEquals(order.getCustomerId(), responseOrder.getCustomerId());
    }


}
