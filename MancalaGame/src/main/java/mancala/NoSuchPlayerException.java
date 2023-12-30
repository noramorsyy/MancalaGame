package mancala;

public class NoSuchPlayerException extends Exception {
    //This class is taken from ChatGPT
    public NoSuchPlayerException() {
        super("No such player found.");
    }

    public NoSuchPlayerException(final String message) {
        super(message);
    }
}
