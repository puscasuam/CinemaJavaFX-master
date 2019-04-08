package UI.Console;

public class ConsoleException extends RuntimeException {
    public ConsoleException(String message){
        super("ConsoleException ||| "+message);
    }
}
