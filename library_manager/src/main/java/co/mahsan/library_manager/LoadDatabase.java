package co.mahsan.library_manager;

import co.mahsan.library_manager.model.Book;
import co.mahsan.library_manager.model.Publisher;
import co.mahsan.library_manager.repository.BookRepository;
import co.mahsan.library_manager.model.Writer;
import co.mahsan.library_manager.repository.PublisherRepository;
import co.mahsan.library_manager.repository.WriterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
class LoadDatabase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final BookRepository bookRepo;
    private final PublisherRepository publisherRepo;
    private final WriterRepository writerRepo;
    public LoadDatabase(BookRepository bookRepo, PublisherRepository publisherRepo, WriterRepository writerRepo) {
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
        this.writerRepo = writerRepo;
    }


    @Override
    public void run(String... args) throws Exception {

        Book newBook1 = new Book("Introduction to Parallel Computing: From Algorithms to Programming on State-of-the-Art Platforms",
                publisherRepo.save(new Publisher().setName("p05")),
                Arrays.asList(
                        writerRepo.save(new Writer().setName("Borut Robič")),
                        writerRepo.save(new Writer().setName("Patricio Bulić")),
                        writerRepo.save(new Writer().setName("Roman Trobec"))));

        log.info("Preloading " + bookRepo.save(newBook1));
        log.info("Preloading " + bookRepo.save(new Book("Introduction to Algorithms",
                publisherRepo.save(new Publisher().setName("Springer")),
                Arrays.asList(writerRepo.save(new Writer().setName("Thomas Cormen")),
                        writerRepo.save(new Writer().setName("Charles Leiserson")),
                        writerRepo.save(new Writer().setName("Ronald Rivest")),
                        writerRepo.save(new Writer().setName("Clifford Stein"))))));
    }
}
