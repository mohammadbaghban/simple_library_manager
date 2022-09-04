package co.mahsan.library_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;


@SpringBootApplication
public class LibraryManagerApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibraryManagerApplication.class, args);
    }

}
