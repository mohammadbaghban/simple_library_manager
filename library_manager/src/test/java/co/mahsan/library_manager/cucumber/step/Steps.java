package co.mahsan.library_manager.cucumber.step;

import co.mahsan.library_manager.controller.BookController;
import co.mahsan.library_manager.model.BookDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class Steps {

    private final BookController bookController;
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
        //placeholder for before scenario logic
    }

    //this method executes before every step
    @BeforeStep
    public void beforeStep() {
        log.info(">>> BeforeStep!");
        //placeholder for before step logic
    }

    @When("the client calls book {string}")
    public void theClientCallsBook(String arg0) throws JsonProcessingException {

        ResponseEntity<String> response = executeGet("http://localhost:8080/books/" + arg0);
        ObjectMapper objectMapper = new ObjectMapper();
        BookDTO bookDTO = objectMapper.readValue(response.getBody(), BookDTO.class);
        System.out.println("book received " + bookDTO.getName());
        assertEquals(bookDTO.getName(), "new book");
    }


    @When("the client calls books")
    public void theClientCallsBooks() {
        System.out.println("In call books");
        assertTrue(false);

    }

    public ResponseEntity<String> executeGet(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, String.class);
    }
}
