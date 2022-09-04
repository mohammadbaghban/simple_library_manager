package co.mahsan.library_manager.exception;

public class PublisherNotFoundException extends RuntimeException{
    public PublisherNotFoundException(String id) {
        super("Could not find publisher " + id);
    }
}
