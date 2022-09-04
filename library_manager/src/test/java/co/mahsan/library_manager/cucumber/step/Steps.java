package co.mahsan.library_manager.cucumber.step;

import co.mahsan.library_manager.controller.BookController;
import co.mahsan.library_manager.model.BookDTO;
import co.mahsan.library_manager.repository.BookRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class Steps {

    private final BookController bookController;
    private final BookRepository bookRepository;
    private String lastBookName = "";
    private String lastBookId = "";
    private HttpStatus lastStatusCode;
    private final String baseUrl = "http://localhost:8080";
    //this method executes after every scenario
    @After
    public void cleanUp() {
        log.info(">>> cleaning up after scenario!");
    }

    //this method executes after every step
    @AfterStep
    public void afterStep() {
        log.info(">>> AfterStep!");
        //placeholder for after step logic
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
        BookDTO newBookDTO = new BookDTO();
        newBookDTO.setName(lastBookName);
        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = mapper.writeValueAsString(newBookDTO);
        System.out.println("new bookdto " + jsonInString);
        ResponseEntity<String> response = executePost(baseUrl + "/books/", jsonInString);
        lastBookId = mapper.readValue(response.getBody(), BookDTO.class).getId();

    }

    @Then("check the status code to be OK")
    public void checkStatusCodeToBeOK() {

        assertEquals(lastStatusCode, HttpStatus.OK);
    }


    @When("the client calls book by id")
    public void theClientCallsBookById() throws JsonProcessingException {

        ResponseEntity<String> response = executeGet(baseUrl + "/books/" + lastBookId);
        ObjectMapper objectMapper = new ObjectMapper();
        BookDTO bookDTO = objectMapper.readValue(response.getBody(), BookDTO.class);
        System.out.println("book received " + bookDTO.getName());
        assertEquals(bookDTO.getName(), lastBookName);
    }


    @When("the client calls books")
    public void theClientCallsBooks() throws JsonProcessingException {
        ResponseEntity<String> response = executeGet(baseUrl + "/books/");
        ObjectMapper objectMapper = new ObjectMapper();
        List<BookDTO> booksDTO = objectMapper.readValue(response.getBody(), new TypeReference<List<BookDTO>>(){});
        System.out.println("books received " + booksDTO.get(0));
        //assertEquals(bookDTO.getName(), "new book");

    }

    @Then("check if new book is in database")
    public void checkIfNewBookIsInDB() {
        assertFalse(bookRepository.findByName(lastBookName).isEmpty());
    }

    public ResponseEntity<String> executeGet(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =  restTemplate.getForEntity(url, String.class);
        lastStatusCode = response.getStatusCode();
        return response;
    }

    public ResponseEntity<String> executePost(String url, String requestJson) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity ,String.class);
        lastStatusCode = response.getStatusCode();
        return response;
    }
}
