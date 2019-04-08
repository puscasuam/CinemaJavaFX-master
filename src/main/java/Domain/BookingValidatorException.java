package Domain;

class BookingValidatorException extends RuntimeException {
    BookingValidatorException(String message) {
        super("BookingValidatorException ||| " + message);
    }
}
