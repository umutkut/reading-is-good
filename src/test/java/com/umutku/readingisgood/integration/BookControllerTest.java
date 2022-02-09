package com.umutku.readingisgood.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umutku.readingisgood.ReadingIsGoodApplication;
import com.umutku.readingisgood.controller.BookController;
import com.umutku.readingisgood.domain.Book;
import com.umutku.readingisgood.dto.BookDTO;
import com.umutku.readingisgood.infrastructure.BookRepository;
import com.umutku.readingisgood.response.RestResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    BookController bookController;
    @MockBean
    BookRepository bookRepository;


    private BookDTO bookDTO;
    private Book book;

    @BeforeEach
    void prepare(){
        String title = "TestTitle";
        String author = "TestAuthor";
        Date publishedDate = Date.from(Instant.now());
        bookDTO = new BookDTO(title, author, publishedDate);
        book = Book.fromDTO(bookDTO);
    }

    @Test
    void testCreateBook() throws JSONException, JsonProcessingException {

        //Mock repository
        Mockito.when(bookRepository.save(book)).thenReturn(book);

        //Call endpoint
        HttpEntity<BookDTO> entity = new HttpEntity<>(bookDTO, headers);

        ResponseEntity<String>  response = restTemplate.exchange(
                createURLWithPort("/api/v1/book"),
                HttpMethod.POST, entity, String.class);

        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        Assertions.assertEquals("CREATED", responseObj.get("status"));

        Book responseBook = new ObjectMapper().readValue(responseObj.get("data").toString(), Book.class);
        Assertions.assertEquals(book, responseBook);
    }

    @Test
    void testGetBook() throws JSONException, JsonProcessingException {

        //Mock repository
        Mockito.when(bookRepository.findById(0l)).thenReturn(Optional.of(book));

        //Call endpoint
        HttpEntity<BookDTO> entity = new HttpEntity<>(bookDTO, headers);

        ResponseEntity<String>  response = restTemplate.exchange(
                createURLWithPort("/api/v1/book?id=0"),
                HttpMethod.GET, entity, String.class);

        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        Assertions.assertEquals("OK", responseObj.get("status"));

        Book responseBook = new ObjectMapper().readValue(responseObj.get("data").toString(), Book.class);
        Assertions.assertEquals(book, responseBook);
    }

    @Test
    void testUpdateBook() throws JSONException, JsonProcessingException {
        //Given
        String updatedTitle = "UpdatedTitle";
        String author = "TestAuthor";
        var publishedDate = Date.from(Instant.now());
        var updatedDTO = new BookDTO(updatedTitle, author, publishedDate);
        var updatedBook = Book.fromDTO(updatedDTO);

        //Mock repository
        Mockito.when(bookRepository.findById(0l)).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.save(updatedBook)).thenReturn(updatedBook);

        //Call endpoint
        HttpEntity<BookDTO> entity = new HttpEntity<>(updatedDTO, headers);

        ResponseEntity<String>  response = restTemplate.exchange(
                createURLWithPort("/api/v1/book?id=0"),
                HttpMethod.PUT, entity, String.class);

        //Assert
        JSONObject responseObj = new JSONObject(response.getBody());
        Assertions.assertEquals("OK", responseObj.get("status"));

        Book responseBook = new ObjectMapper().readValue(responseObj.get("data").toString(), Book.class);
        Assertions.assertEquals(updatedBook, responseBook);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
