package co.mahsan.library_manager;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String id) {
        super("Could not find employee " + id);
    }
}
