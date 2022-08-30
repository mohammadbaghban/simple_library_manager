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


    }
}
