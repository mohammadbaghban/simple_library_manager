package co.mahsan.library_manager.exception; //todo comment: in package behtare bere to util

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String id) {
        super("Could not find book " + id);
    }
}
