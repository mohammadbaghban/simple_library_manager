package co.mahsan.library_manager.cucumber.step;

import co.mahsan.library_manager.mapper.BookMapper;
import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.model.BookDto;
import co.mahsan.library_manager.repository.BookRepository;
import co.mahsan.library_manager.repository.PublisherRepository;
import co.mahsan.library_manager.repository.WriterRepository;
import co.mahsan.library_manager.util.exception.BookNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class Steps {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final WriterRepository writerRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080";
    private BookDto lastBook;
    private HttpStatus lastStatusCode;

    //this method executes after every scenario
    @After
    public void cleanUp() {
        bookRepository.deleteAll();
        publisherRepository.deleteAll();
        writerRepository.deleteAll();
    }

    @Given("client posts a book with name {string}")
    public void postNewBook(String bookName) throws IOException {
        BookDto newBookDto = new BookDto();
        newBookDto.setName(bookName);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(newBookDto);
        ResponseEntity<String> response = executePost(baseUrl + "/books/", jsonInString);
        lastBook = mapper.readValue(response.getBody(), BookDto.class);
    }

    @Then("check the status code to be OK")
    public void checkStatusCodeToBeOK() {
        assertEquals(lastStatusCode, HttpStatus.OK);
    }

    @When("the client calls book by id")
    public void theClientCallsBookById() throws JsonProcessingException {
        ResponseEntity<String> response = executeGet(baseUrl + "/books/" + lastBook.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        lastBook = objectMapper.readValue(response.getBody(), BookDto.class);
    }


    @When("the client gets all books")
    public void theClientCallsBooks() {
        ResponseEntity<String> response = executeGet(baseUrl + "/books/");
        lastStatusCode = response.getStatusCode();
    }

    @Then("check if new book is in database")
    public void checkIfNewBookIsInDB() {
        assertFalse(bookRepository.findAllByName(lastBook.getName()).isEmpty());
    }

    @When("the client requests to update last book name")
    public void theClientRequestsToUpdateLastBook() throws BookNotFoundException, IOException {
        Optional<Book> optionalBook = bookRepository.findById(lastBook.getId());
        BookDto bookDTO;
        if (!optionalBook.isPresent()) {
            throw new BookNotFoundException("Book with this id not found");
        } else {
            bookDTO = BookMapper.INSTANCE.bookToBookDTO(optionalBook.get());
        }
        lastBook.setName(new Date().toString());
        bookDTO.setName(lastBook.getName());
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(bookDTO);
        ResponseEntity<BookDto> response = executePut(baseUrl + "/books/" + lastBook.getId(), jsonInString);
        lastStatusCode = response.getStatusCode();
    }

    @Then("the book is updated")
    public void checkIfTheBookIsUpdated() throws JsonProcessingException {
        ResponseEntity<String> response = executeGet(baseUrl + "/books/" + lastBook.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        BookDto bookDto = objectMapper.readValue(response.getBody(), BookDto.class);
        assertEquals(bookDto.getName(), lastBook.getName());
    }



    @When("the client requests to delete last book")
    public void deleteLastBook() {
        ResponseEntity<BookDto> response = executeDelete(lastBook.getId());
        lastStatusCode = response.getStatusCode();
        assertFalse(bookRepository.findById(lastBook.getId()).isPresent());
    }

    public ResponseEntity<String> executeGet(String url) {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        lastStatusCode = response.getStatusCode();
        return response;
    }

    public ResponseEntity<String> executePost(String url, String requestJson) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        lastStatusCode = response.getStatusCode();
        return response;
    }

    private ResponseEntity<BookDto> executePut(String url, String requestJson) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<BookDto> response = restTemplate.exchange(url, HttpMethod.PUT, entity, BookDto.class);
        lastStatusCode = response.getStatusCode();
        return response;
    }

    private ResponseEntity<BookDto> executeDelete(String id) {
        String entityUrl = "http://localhost:8080/books/" + id;
        ResponseEntity<BookDto> response = restTemplate.exchange(entityUrl, HttpMethod.DELETE, null, BookDto.class);
        lastStatusCode = response.getStatusCode();
        return response;
    }

    @Then("check the received book name has an id and its name is {string}")
    public void checkTheReceivedBookNameHasAnIdAndItsNameIsCorrect(String bookName) {
        assertNotNull(lastBook.getId());
        assertEquals(lastBook.getName(), bookName);
    }

    @Then("the book must not be in GET request")
    public void theBookMustNotBeInGETRequest() {
        try {
            executeGet(baseUrl + "/books/" + lastBook.getId());
        } catch (HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
