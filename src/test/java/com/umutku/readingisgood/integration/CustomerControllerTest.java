package com.umutku.readingisgood.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umutku.readingisgood.ReadingIsGoodApplication;
import com.umutku.readingisgood.domain.Customer;
import com.umutku.readingisgood.dto.CustomerDTO;
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
class CustomerControllerTest {

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
    void testRegisterHappyPath() throws JSONException, JsonProcessingException {

        //Mock repository
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        //Call endpoint
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(customerDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                TestUtils.createURLWithPort("/api/v1/customers/register", port),
                HttpMethod.POST, entity, String.class);

        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        Assertions.assertEquals("CREATED", responseObj.get("status"));

        Customer responseCustomer = new ObjectMapper().readValue(responseObj.get("data").toString(), Customer.class);
        Assertions.assertEquals(customer, responseCustomer);
    }


    @Test
    void testInvalidUserName() throws JSONException {

        //Mock repository
        Mockito.when(customerRepository.findCustomerByUserName(customer.getUserName())).thenReturn(Optional.of(customer));

        //Call endpoint
        HttpEntity<CustomerDTO> entity = new HttpEntity<>(customerDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                TestUtils.createURLWithPort("/api/v1/customers/register", port),
                HttpMethod.POST, entity, String.class);

        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        Assertions.assertEquals("CONFLICT", responseObj.get("status"));
    }


}
