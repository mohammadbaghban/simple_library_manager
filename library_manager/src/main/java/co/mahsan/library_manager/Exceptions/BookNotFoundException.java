package co.mahsan.library_manager.Exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String id) {
        super("Could not find book " + id);
    }
}
