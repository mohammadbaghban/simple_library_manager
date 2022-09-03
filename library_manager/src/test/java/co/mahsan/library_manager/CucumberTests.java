package co.mahsan.library_manager;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class CucumberTests {

    @Test
    void contextLoads() {
        System.out.println("In SpringBootTest Context Load Test");
    }

}
