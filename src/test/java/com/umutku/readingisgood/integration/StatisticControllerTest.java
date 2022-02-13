package com.umutku.readingisgood.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umutku.readingisgood.ReadingIsGoodApplication;
import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.dto.BookDTO;
import com.umutku.readingisgood.dto.CustomerDTO;
import com.umutku.readingisgood.infrastructure.BookRepository;
import com.umutku.readingisgood.infrastructure.CustomerRepository;
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

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StatisticControllerTest {

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    @MockBean
    CustomerRepository customerRepository;
    @LocalServerPort
    private int port;

    private CustomerDTO customerDTO;
    private Customer customer;

    @BeforeEach
    void prepare() {
        String username = "UserName";
        String password = "Password";
        customerDTO = new CustomerDTO(username, password, null, null);
        customer = Customer.fromDTO(customerDTO);
    }

    @Test
    void testGetTotalNumberOfOrder() throws JSONException, JsonProcessingException {
        //Mock repository
        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        //Call endpoint
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                TestUtils.createURLWithPort("/api/v1/statistics/"+customer.getId()+"/totalOrderCount", port),
                HttpMethod.GET, entity, String.class);

        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        Assertions.assertEquals("OK", responseObj.get("status"));

        Integer totalOrderCount = new ObjectMapper().readValue(responseObj.get("data").toString(), Integer.class);
        Assertions.assertEquals(customer.getOrderCount(), totalOrderCount);
    }
    @Test
    void testGetTotalNumberOfOrderCustomerNotFound() throws JSONException {
        //Mock repository
        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.empty());

        //Call endpoint
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                TestUtils.createURLWithPort("/api/v1/statistics/"+customer.getId()+"/totalOrderCount", port),
                HttpMethod.GET, entity, String.class);

        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        Assertions.assertEquals("NOT_FOUND", responseObj.get("status"));

    }
}
