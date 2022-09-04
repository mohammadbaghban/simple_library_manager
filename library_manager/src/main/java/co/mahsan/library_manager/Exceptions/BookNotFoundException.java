package co.mahsan.library_manager.Exceptions; // todo comment: package naming. bia ba ham sohbat konim

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String id) {
        super("Could not find book " + id);
    }
}
