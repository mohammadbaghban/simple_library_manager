package co.mahsan.library_manager;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepDefsIntegrationTest extends SpringIntegrationTest{
    @When("^the client calls /books$")
    public void the_client_issues_GET_hello() throws Throwable {
        executeGet("http://localhost:8080/books");
    }

//    @When("^the client calls /version$")
//    public void the_client_issues_GET_version() throws Throwable {
//        executeGet("http://localhost:8080/version");
//    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }
}
