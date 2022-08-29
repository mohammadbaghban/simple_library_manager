package co.mahsan.library_manager;

import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.model.Publisher;
import co.mahsan.library_manager.repository.BookRepository;
import co.mahsan.library_manager.model.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Book("Introduction to Parallel Computing: From Algorithms to Programming on State-of-the-Art Platforms",
                new Publisher("Springer"), Arrays.asList(new Writer("Borut", "Robič"), new Writer("Patricio", "Bulić"), new Writer("Roman", "Trobec")))));
            log.info("Preloading " + repository.save(new Book("Introduction to Algorithms",
               new Publisher("Springer"), Arrays.asList(new Writer("Thomas", "Cormen"), new Writer("Charles", "Leiserson"), new Writer("Ronald", "Rivest"), new Writer("Clifford", "Stein")))));
        };
    }
}
