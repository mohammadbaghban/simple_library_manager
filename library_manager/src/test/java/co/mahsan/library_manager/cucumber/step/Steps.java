package co.mahsan.library_manager.cucumber.step;

import co.mahsan.library_manager.exception.BookNotFoundException;
import co.mahsan.library_manager.mapper.BookMapper;
import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.model.BookDto;
import co.mahsan.library_manager.repository.BookRepository;
import co.mahsan.library_manager.repository.PublisherRepository;
import co.mahsan.library_manager.repository.WriterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class Steps {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final WriterRepository writerRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private String lastBookName = "";
    private String lastBookId = "";
    private HttpStatus lastStatusCode;
    private final String baseUrl = "http://localhost:8080";

    //this method executes after every scenario
    @After
    public void cleanUp() {
        bookRepository.deleteAll();
        publisherRepository.deleteAll();
        writerRepository.deleteAll();
    }

    //this method executes after every step
    @AfterStep
    public void afterStep() {

    }

    //this method executes before every scenario
    @Before
    public void before() {
        log.info(">>> Before scenario!");
        lastBookName = new Date().toString();
    }

    //this method executes before every step
    @BeforeStep
    public void beforeStep() {
        log.info(">>> BeforeStep!");
        //placeholder for before step logic
    }

    @Given("execute posting a new book")
    public void postNewBook() throws IOException {
        BookDto newBookDto = new BookDto();
        newBookDto.setName(lastBookName);
        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = mapper.writeValueAsString(newBookDto);
        System.out.println("new bookdto " + jsonInString);
        ResponseEntity<String> response = executePost(baseUrl + "/books/", jsonInString);
        lastBookId = mapper.readValue(response.getBody(), BookDto.class).getId();

    }

    @Then("check the status code to be OK")
    public void checkStatusCodeToBeOK() {

        assertEquals(lastStatusCode, HttpStatus.OK);
    }


    @When("the client calls book by id")
    public void theClientCallsBookById() throws JsonProcessingException {

        ResponseEntity<String> response = executeGet(baseUrl + "/books/" + lastBookId);
        ObjectMapper objectMapper = new ObjectMapper();
        BookDto bookDTO = objectMapper.readValue(response.getBody(), BookDto.class);
        System.out.println("book received " + bookDTO.getName());
        assertEquals(bookDTO.getName(), lastBookName);
    }


    @When("the client calls books")
    public void theClientCallsBooks() throws JsonProcessingException {
        ResponseEntity<String> response = executeGet(baseUrl + "/books/");
        ObjectMapper objectMapper = new ObjectMapper();
        List<BookDto> booksDTO = objectMapper.readValue(response.getBody(), new TypeReference<List<BookDto>>(){});
        System.out.println("books received " + booksDTO.get(0));
        //assertEquals(bookDTO.getName(), "new book");

    }

    @Then("check if new book is in database")
    public void checkIfNewBookIsInDB() {
        assertFalse(bookRepository.findAllByName(lastBookName).isEmpty());
    }

    @When("the client requests to update last book name")
    public void theClientRequestsToUpdateLastBook() throws BookNotFoundException, IOException {
        Optional<Book> optionalBook = bookRepository.findById(lastBookId);
        BookDto bookDTO;
        if (!optionalBook.isPresent()) {
            throw new BookNotFoundException("Book with this id not found");
        } else {
            bookDTO = BookMapper.INSTANCE.bookToBookDTO(optionalBook.get());
        }
        lastBookName = new Date().toString();
        bookDTO.setName(lastBookName);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(bookDTO);
        HttpEntity<BookDto> response = executePut(baseUrl + "/books/" + lastBookId, jsonInString);
        assertEquals(response.getBody().getName(), lastBookName);
    }

    @When("the client requests to delete last book")
    public void deleteLastBook() {
        ResponseEntity<BookDto> response = executeDelete(baseUrl + "/books/", lastBookId);
        assertFalse(bookRepository.findById(lastBookId).isPresent());
    }

    public ResponseEntity<String> executeGet(String url) {
        ResponseEntity<String> response =  restTemplate.getForEntity(url, String.class);
        lastStatusCode = response.getStatusCode();
        return response;
    }

    public ResponseEntity<String> executePost(String url, String requestJson) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity ,String.class);
        lastStatusCode = response.getStatusCode();
        return response;
    }

    public ResponseEntity<BookDto> executePut(String url, String requestJson) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<BookDto> response = restTemplate.exchange(url, HttpMethod.PUT, entity, BookDto.class);
        lastStatusCode = response.getStatusCode();
        return response;
    }

    public ResponseEntity<BookDto> executeDelete(String url, String id) {
        String entityUrl = url + id;
        ResponseEntity<BookDto> response = restTemplate.exchange(entityUrl, HttpMethod.DELETE, null, BookDto.class);
        lastStatusCode = response.getStatusCode();
        return response;
    }
}
