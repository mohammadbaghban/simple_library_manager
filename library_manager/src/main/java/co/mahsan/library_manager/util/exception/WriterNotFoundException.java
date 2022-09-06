package co.mahsan.library_manager.util.exception;

public class WriterNotFoundException extends RuntimeException{
    public WriterNotFoundException(String id) {
        super("Could not find writer " + id);
    }
}
