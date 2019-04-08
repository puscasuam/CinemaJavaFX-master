package Domain;

public class MovieValidatorException extends RuntimeException {
    MovieValidatorException(String message) {
        super("MovieValidatiorException ||| " + message);
    }
}
