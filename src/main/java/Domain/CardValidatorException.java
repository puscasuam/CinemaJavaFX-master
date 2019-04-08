package Domain;

class CardValidatorException extends RuntimeException {
    CardValidatorException(String message) {
        super("CardValidatorException ||| " + message);
    }
}
